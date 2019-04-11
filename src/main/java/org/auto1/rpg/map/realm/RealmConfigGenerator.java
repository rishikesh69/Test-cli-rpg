package org.auto1.rpg.map.realm;

import static org.auto1.rpg.common.utils.CommonUtil.formatString;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.auto1.rpg.map.character.EnemyConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RealmConfigGenerator {

	private final static String xmlPath = "src/main/resources/Realmconfig.xml";

	private RealmConfigGenerator() {
	}

	public static List<RealmConfiguration> realms() {
		List<RealmConfiguration> realmConfigurations = new ArrayList<>();
		try {
			realmConfigurations = parseXml(xmlPath);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		// TODO: Handle better
		return realmConfigurations;
	}

	private static EnemyConfiguration buildEnemy(String name, String desc, String greeting, int hp, int damage,
			int dmgVariation) {
		return new EnemyConfiguration(name, desc, greeting, hp, damage, dmgVariation);
	}

	private static RealmConfiguration buildRealmConfiguration(String name, List<EnemyConfiguration> definedEnemies) {
		List<EnemyConfiguration> enemies = new ArrayList<>();
		enemies.addAll(definedEnemies);
		return new RealmConfiguration(name, enemies.size(), enemies);
	}

	private static List<RealmConfiguration> parseXml(String xmlPath)
			throws ParserConfigurationException, SAXException, IOException {
		Document document = getDocument(xmlPath);
		document.getDocumentElement().normalize();
		NodeList gameList = document.getElementsByTagName("Game");
		List<RealmConfiguration> realmConfigList = new ArrayList<>();
		RealmConfiguration realmConfig = null;
		for (int temp = 0; temp < gameList.getLength(); temp++) {
			Node node = gameList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				String realmName = formatString(eElement.getElementsByTagName("Realm").item(0).getTextContent());

				NodeList enemyList = eElement.getElementsByTagName("Enemy");
				List<EnemyConfiguration> enemyConfigList = new ArrayList<>();
				IntStream.range(0, enemyList.getLength()).mapToObj(enemyList::item)
						.map(innerNode -> (Element) innerNode).forEach(innerElement -> {
							String enemyName = formatString(
									innerElement.getElementsByTagName("Name").item(0).getTextContent());
							String desc = formatString(
									innerElement.getElementsByTagName("desc").item(0).getTextContent());
							String greeting = formatString(
									innerElement.getElementsByTagName("greeting").item(0).getTextContent());
							int maxHp = Integer
									.valueOf(innerElement.getElementsByTagName("maxHp").item(0).getTextContent());
							int damage = Integer
									.valueOf(innerElement.getElementsByTagName("damage").item(0).getTextContent());
							int damageVariation = Integer.valueOf(
									innerElement.getElementsByTagName("damageVariation").item(0).getTextContent());
							EnemyConfiguration enemyConfig = buildEnemy(enemyName, desc, greeting, maxHp, damage,
									damageVariation);
							enemyConfigList.add(enemyConfig);
						});
				realmConfig = buildRealmConfiguration(realmName, enemyConfigList);
			}
			realmConfigList.add(realmConfig);
		}
		return realmConfigList;
	}

	/**
	 * @param xmlPath
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private static Document getDocument(String xmlPath) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(xmlPath));
		return document;
	}

}
