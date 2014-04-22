package com.github.thelonedevil.TwitchBot;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pircbotx.Channel;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Hello world!
 * 
 */
@SuppressWarnings("rawtypes")
public class App extends ListenerAdapter implements Listener {

	static PircBotX bot;
	static Channel channel;
	private static Database sql = new Database();
	public static Statement statement;
	public static Connection connection;

	public static void main(String[] args) {
		/*
		 * bot = new PircBotX(); System.out.println("bot on");
		 * bot.setName("Lone_Bot"); System.out.println("bot name set");
		 * bot.getListenerManager().addListener(new App());
		 * System.out.println("bot listyener"); try {
		 * bot.connect("irc.twitch.tv"
		 * ,6667,"oauth:maix34hehqfj1rehor730c8lv2wbuy8"); } catch (Exception e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); }
		 * System.out.println("bot connected");
		 * bot.joinChannel("#The_Lone_Devil"); System.out.println("bot joined");
		 * channel = bot.getChannel("#The_Lone_Devil");
		 * channel.sendMessage("I am here");
		 */
		try {
			load();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("unchecked")
		Configuration<?> configuration = new Configuration.Builder().setName("Lone_Bot").setAutoNickChange(false).setCapEnabled(false).addListener(new App()).setServerHostname("irc.twitch.tv")
				.setServerPassword("oauth:maix34hehqfj1rehor730c8lv2wbuy8").addAutoJoinChannel("#the_lone_devil").buildConfiguration();
		System.out.println("Bot config built");
		bot = new PircBotX(configuration);

		try {
			bot.startBot();
		} catch (Exception ex) {
			System.out.println("error happened");
			ex.printStackTrace();
		}
	}

	private boolean isMod(User user, Channel channel) {
		Set channels = user.getChannelsOpIn();
		if (channels.contains(channel)) {
			return true;
		} else
			return false;

	}

	@SuppressWarnings("unchecked")
	public static void load() throws SQLException, ClassNotFoundException, NullPointerException, IOException {
		String path = "commands.sqlite";
		File file = new File(path);

		file.createNewFile();

		connection = sql.connect(path);

		statement = sql.state(connection, 30);

		sql.createTable(statement, "the_lone_devil", "comamnd", "string", "response", "string");
		sql.createTable(statement, "lone_bot", "command", "string", "response", "string");
		/*
		 * ObjectInputStream ois; try { ois = new ObjectInputStream(new
		 * FileInputStream("chanelcommands.yaml"));
		 * 
		 * Yaml yaml = new Yaml(); channels = (HashMap<String, HashMap<String,
		 * String>>) yaml.load(ois); } catch (FileNotFoundException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (IOException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	public void save(HashMap map) {
		/*
		 * Yaml yaml = new Yaml(); String output = yaml.dump(map); File out =
		 * new File("channelcommands.yaml"); try { ObjectOutputStream oos = new
		 * ObjectOutputStream(new FileOutputStream(out));
		 * oos.writeObject(output); oos.flush(); oos.close(); } catch
		 * (FileNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
	}

	@Override
	public void onMessage(MessageEvent event) {
		Channel channel = event.getChannel();
		String channel1 = channel.getName();
		if (!(event.getMessage().startsWith("!comAdd") || event.getMessage().equalsIgnoreCase("!Lone_Bot leave") || event.getMessage().equalsIgnoreCase("!stop") || event.getMessage()
				.equalsIgnoreCase("!join"))) {
			String[] comname = event.getMessage().split(" ");
			String query = "SELECT * FROM " + channel1.replace('#', ' ').trim() + ";";
			try {
				ResultSet rs = Database.rs(statement, query);
				while (rs.next()) {
					if(rs.getString("command").equalsIgnoreCase("!"+comname[0])){
						String response = rs.getString("response");
						String[] com = response.split(":");
						if(com[0].equalsIgnoreCase("ul=mod") && isMod(event.getUser(),channel)){
							if(com[1].equalsIgnoreCase("type=normal")){
								channel.send().message(com[2]);
							}else if(com[1].equalsIgnoreCase("type=respond")){
								event.respond(com[2]);
							}
						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (event.getMessage().startsWith("!comAdd")) {
			String command3 = event.getMessage().substring(7);
			String[] thing1 = event.getMessage().split("\'");
			String[] thing = thing1[0].split(":");
			if (command3 != null && thing[1] != null) {
				String comname = thing[1];
				String outputmessage = thing1[1];
				String comtype = thing[2];
				String comul = thing[0];
				String tosave = comul + ":" + comtype + ":" + outputmessage;
				try {
					sql.insertRow(statement, channel1.replace('#', ' ').trim(), comname , "command", "' "+tosave.replace("'", "''"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				event.respond(comname + " has been created");
				event.respond(comname + " does " + outputmessage);

				// !comAdd ul=Mod/User:!test:type=Normal/Respond:"test"
			} else

			if (event.getMessage().equalsIgnoreCase("!join")) {
				String name = event.getUser().getNick();
				try {
					sql.createTable(statement, name, "comamnd", "string", "response", "string");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				event.respond("Your channel has been added");

			} else if (event.getMessage().equalsIgnoreCase("!stop")) {
				if (event.getUser().getNick().equalsIgnoreCase("the_lone_devil")) {
					event.respond("I must go, my people need me!");
					System.exit(0);
				} else {
					event.respond("Only My Owner: The_Lone_Devil can use that command");

				}
			} else

			/*
			 * if (event.getMessage().equalsIgnoreCase("!Link")) { if
			 * (isMod(event.getUser(), event.getChannel())) {
			 * event.getChannel().send().message(
			 * "The_Lone_Devil's Twitter is: http://www.twitter.com/the_lone_devil"
			 * );
			 * 
			 * }
			 */

			if (event.getMessage().equalsIgnoreCase("!Lone_Bot-leave")) {
				if (isMod(event.getUser(), event.getChannel())) {
					event.respond("I will leave now at the request of a Moderator of this channel");
					event.getChannel().send().part();
				}
			}
			if (event.getMessage().contains("hello ") || event.getMessage().contains("hi ") || event.getMessage().contains("hey ") || event.getMessage().equalsIgnoreCase("hello")
					|| event.getMessage().equalsIgnoreCase("hi") || event.getMessage().equalsIgnoreCase("hey")) {
				event.respond("Welcome to The_Lone_Devil's Channel");
			}
			if (event.getMessage().equalsIgnoreCase("!Support")) {
				event.getChannel()
						.send()
						.message(
								"Please show some support to these awesome streamers: Aureylian (http://www.twitch.tv/Aureylian), TheSpriteful (http://www.twitch.tv/TheSpriteful), TrunksWD (http://www.twitch.tv/TrunksWD), Josh (http://www.twitch.tv/josht555), Arron (http://www.twitch.tv/pythonsoul, Mike (http://www.twitch.tv/elthilwell))");
			}
			if (event.getMessage().equalsIgnoreCase("!glitter")) {
				event.getChannel().send().message("☆*･゜ﾟ･*\\(^O^)/*･゜ﾟ･*☆");
			}
			if (event.getMessage().equalsIgnoreCase("!dualTrunkswd")) {
				event.getChannel().send().message("http://kbmod.com/multistream/view/?s0=The_lone_devil&s1=trunkswd&layout=4");
			}
			if (event.getMessage().equalsIgnoreCase("!Hive")) {
				event.getChannel()
						.send()
						.message(
								"The_Lone_Devil is playing on The Hive minigame server, to join please connect to play.hivemc.com, the lobby for the next game will be put into chat either by The_lone_devil or another mod.");
			}
			if (event.getMessage().equalsIgnoreCase("!Mineplex")) {
				event.getChannel()
						.send()
						.message(
								"The_Lone_Devil is playing on The Mineplex minigame server, to join please connect to us.mineplex.com, and join to lobby 35. If there is space in the party, post your IGN in chat and you may be added to the paryt");
			}
			if (event.getMessage().equalsIgnoreCase("!Shotbow")) {
				event.getChannel()
						.send()
						.message(
								"The_Lone_Devil is playing on The Shotbow minigame server, to join please connect to eu.shotbow.net, the lobby for the next game will be put into chat either by The_lone_devil or another mod.");
			}
			if (event.getMessage().equalsIgnoreCase("!Vector")) {
				event.getChannel()
						.send()
						.message(
								"The_Lone_Devil is playing on The Vector Sector minigame server, to join please connect to play.vector-mc.com, the lobby for the next game will be put into chat either by The_lone_devil or another mod.");
			}
			if (event.getMessage().equalsIgnoreCase("!PMC")) {
				event.getChannel()
						.send()
						.message(
								"The_Lone_Devil is playing on The Play Mindcrack minigame server, to join please connect to us.playmindcrack.com, the lobby for the next game will be put into chat either by The_lone_devil or another mod.");
			}
			if (event.getMessage().equalsIgnoreCase("!glittercraft")) {
				event.getChannel().send().message("The_Lone_Devil is playing on The Glittercraft server, unfortunately this is a whitelisted server for Subscribers of the Broadcaster Aureylian");
			}

			// timeouts
			if (!isMod(event.getUser(), channel)) {
				if (event.getMessage().contains(" ")) {

					String[] message = event.getMessage().split(" ");
					for (int i = 0; !message[i].isEmpty(); i++) {
						Pattern pattern = Pattern.compile("(?:(())(www\\.([^/?#\\s]*))|((http(s)?|ftp):)(//([^/?#\\s]*)))([^?#\\s]*)(\\?([^#\\s]*))?(#([^\\s]*))?");
						Pattern pattern1 = Pattern.compile("^[a-zA-Z0-9\\-\\.]+\\.(com|org|net|mil|edu|co.uk|be|ly|tk|COM|ORG|NET|MIL|EDU|CO.UK|TK|BE|LY)$");
						Matcher matcher = pattern.matcher(event.getMessage());
						Matcher matcher1 = pattern1.matcher(event.getMessage());
						if (matcher.find() || matcher1.find()) {
							event.respond("That is a link, and you are not a Mod, so stop posting it");
							event.getChannel().send().message("/timeout " + event.getUser().getNick() + " 1");
						}
					}
				} else {
					Pattern pattern = Pattern.compile("(?:(())(www\\.([^/?#\\s]*))|((http(s)?|ftp):)(//([^/?#\\s]*)))([^?#\\s]*)(\\?([^#\\s]*))?(#([^\\s]*))?");
					Pattern pattern1 = Pattern.compile("^[a-zA-Z0-9\\-\\.]+\\.(com|org|net|mil|edu|co.uk|be|ly|tk|COM|ORG|NET|MIL|EDU|CO.UK|TK|BE|LY)$");
					Matcher matcher = pattern.matcher(event.getMessage());
					Matcher matcher1 = pattern1.matcher(event.getMessage());
					if (matcher.find() || matcher1.find()) {
						event.respond("That is a link, and you are not a Mod, so stop posting it");
						event.getChannel().send().message("/timeout " + event.getUser().getNick() + " 1");
					} else {

					}
				}
			}
		}

	}

	public static void shutdown() {
		System.exit(0);
	}

}
