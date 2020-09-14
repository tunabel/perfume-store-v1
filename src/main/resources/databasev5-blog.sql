-- MySQL dump 10.13  Distrib 8.0.20, for Linux (x86_64)
--
-- Host: localhost    Database: perfume_store
-- ------------------------------------------------------
-- Server version	8.0.20-0ubuntu0.19.10.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dbo_blog`
--

DROP TABLE IF EXISTS `dbo_blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dbo_blog` (
  `blog_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `short_desc` text NOT NULL,
  `short_img` varchar(255) NOT NULL,
  `full_desc` text NOT NULL,
  `full_img` varchar(255) NOT NULL,
  `status` tinyint NOT NULL,
  `created_date` date NOT NULL,
  PRIMARY KEY (`blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_blog`
--

LOCK TABLES `dbo_blog` WRITE;
/*!40000 ALTER TABLE `dbo_blog` DISABLE KEYS */;
INSERT INTO `dbo_blog` VALUES (1,'Hello','Hi from us','images/bg_6.jpg','Hi hi hi there again','images/bg_6.jpg',1,'2020-09-13');
/*!40000 ALTER TABLE `dbo_blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_blog_tag`
--

DROP TABLE IF EXISTS `dbo_blog_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dbo_blog_tag` (
  `blog_tag_id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int NOT NULL,
  `tag_id` int NOT NULL,
  PRIMARY KEY (`blog_tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_blog_tag`
--

LOCK TABLES `dbo_blog_tag` WRITE;
/*!40000 ALTER TABLE `dbo_blog_tag` DISABLE KEYS */;
INSERT INTO `dbo_blog_tag` VALUES (1,1,1),(2,1,2);
/*!40000 ALTER TABLE `dbo_blog_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_cart`
--

DROP TABLE IF EXISTS `dbo_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dbo_cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `guid` varchar(100) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_cart`
--

LOCK TABLES `dbo_cart` WRITE;
/*!40000 ALTER TABLE `dbo_cart` DISABLE KEYS */;
INSERT INTO `dbo_cart` VALUES (5,'77eb299c-5e9a-47ce-9bba-79d69a20e6be',NULL),(7,'22a37495-3d59-4f96-8bf5-061c89a17cdd',NULL),(8,'1b832bdc-e772-4389-8c34-f4334a404b3d',NULL),(9,'4f197078-37a3-4915-9ad9-f62c3ca780c3',NULL),(10,'9bf7bb68-47d9-4135-bf41-327b6385fcdf',NULL),(11,'ed24c50c-d7dc-44de-9eac-8525d20a317e',NULL),(12,'c4ccc1e4-106b-41ee-bc25-ae1289708e3d',NULL),(13,'5e801538-5b80-49c5-88a9-17ea06ce47c2',NULL),(17,'12a78f12-563d-435a-aa2c-9b9470f208ed','tuantuan'),(18,'e1c568a4-9191-4dde-bf2e-22719f26e51e',NULL),(19,'79336667-cc5e-4d2b-9e35-64699049e382','tuan2'),(20,'eeb273d1-8b00-49ce-91c9-2a6f82cae76d',NULL),(21,'8384251c-0fd8-4bd9-accd-06be10b0ad63',NULL),(23,'15ffdc92-e00e-4e4b-bc2e-d8067c4c2c51',NULL),(24,'724e0d33-fb73-447d-8d40-191c87f45b5c',NULL),(25,'7299dbd5-bcfd-4986-b66e-b42d6b23239f',NULL),(26,'564fb764-4763-46ce-9d92-e392ad705cba','tuantuan2'),(27,'2459073e-f01d-4c2f-9b93-8bed25090168',NULL),(28,'3b807ed1-076e-463b-8930-a043e1a9fac1',NULL),(29,'4f7e8629-c4c6-4d40-8116-3b02ec7d7f02',NULL),(30,'8339a0e8-dc05-48be-a6f5-0cc6d7b0fbe8',NULL),(33,'ecf97f37-0c94-43f7-adab-2a73290ae99e',NULL);
/*!40000 ALTER TABLE `dbo_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_cart_sku`
--

DROP TABLE IF EXISTS `dbo_cart_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dbo_cart_sku` (
  `cart_sku_id` int NOT NULL AUTO_INCREMENT,
  `cart_id` int NOT NULL,
  `sku_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`cart_sku_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_cart_sku`
--

LOCK TABLES `dbo_cart_sku` WRITE;
/*!40000 ALTER TABLE `dbo_cart_sku` DISABLE KEYS */;
INSERT INTO `dbo_cart_sku` VALUES (1,2,2,3),(13,9,2,1),(16,11,1,1),(17,12,3,1),(25,26,2,1),(26,26,6,1),(27,26,4,1),(28,26,3,6),(30,19,4,3),(31,19,3,3),(32,19,5,5);
/*!40000 ALTER TABLE `dbo_cart_sku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_order`
--

DROP TABLE IF EXISTS `dbo_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dbo_order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `guid` varchar(50) DEFAULT NULL,
  `promo_code` varchar(45) DEFAULT NULL,
  `payment_method` tinyint NOT NULL,
  `purchase_date` datetime DEFAULT NULL,
  `delivery_date` datetime DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT '0',
  `name` varchar(50) NOT NULL,
  `address` text NOT NULL,
  `phone` varchar(10) NOT NULL,
  `created_date` date NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `total_price` int NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_order`
--

LOCK TABLES `dbo_order` WRITE;
/*!40000 ALTER TABLE `dbo_order` DISABLE KEYS */;
INSERT INTO `dbo_order` VALUES (3,'tuantuan','be7b303c-0e2b-43be-9237-4d4338f58dbc',NULL,0,NULL,NULL,2,'Tuan','123','123','2020-09-04','tuan1@gmail.com',8100),(4,'tuantuan','eff908e4-81fd-48f5-bed6-20968c2c491d',NULL,0,NULL,NULL,0,'Tuan','123','123','2020-09-04','tuan1@gmail.com',0),(5,'tuantuan','84430395-0f20-42e7-8e5b-ea16f941e3d4',NULL,0,NULL,NULL,2,'Tuan','123','123','2020-09-05','tuan1@gmail.com',1900),(6,'tuantuan','f75a0ce6-f03b-4d8b-a4e4-eeaf103e7fb2',NULL,0,NULL,NULL,0,'Tuan','123','123','2020-08-05','tuan1@gmail.com',8100),(7,NULL,'9875f70b-41c5-4c9c-b449-29250d225edc',NULL,0,NULL,NULL,0,'Minh Meo','Tay Ho','123123','2020-09-13','meomeo@gmail.com',3000),(8,NULL,'a53f7532-74fa-4fe7-9325-3423e5617591',NULL,0,NULL,NULL,0,'Hoang','hoangga@Gmail.com','123123123','2020-09-13','gaga@gmail.com',3100);
/*!40000 ALTER TABLE `dbo_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_order_sku`
--

DROP TABLE IF EXISTS `dbo_order_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dbo_order_sku` (
  `order_sku_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `sku_id` int NOT NULL,
  `price` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`order_sku_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_order_sku`
--

LOCK TABLES `dbo_order_sku` WRITE;
/*!40000 ALTER TABLE `dbo_order_sku` DISABLE KEYS */;
INSERT INTO `dbo_order_sku` VALUES (3,3,2,1200,1),(4,3,4,1500,1),(5,3,1,8100,10),(6,5,5,700,1),(7,5,6,1900,1),(8,6,4,300,1),(9,6,2,1500,1),(10,6,1,8100,10),(11,7,10,1600,1),(12,7,9,1400,1),(13,8,10,1600,1),(14,8,11,1500,2);
/*!40000 ALTER TABLE `dbo_order_sku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_product`
--

DROP TABLE IF EXISTS `dbo_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dbo_product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `type_id` tinyint NOT NULL,
  `scent_id` tinyint NOT NULL,
  `brand_id` int NOT NULL,
  `gender` tinyint NOT NULL DEFAULT '0',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci,
  `created_date` datetime NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_product`
--

LOCK TABLES `dbo_product` WRITE;
/*!40000 ALTER TABLE `dbo_product` DISABLE KEYS */;
INSERT INTO `dbo_product` VALUES (1,4,12,5,1,'Colonia','Best fragrance!','2020-09-12 15:11:18'),(2,3,12,5,2,'Arancia di Capri','1234','2020-09-12 15:11:37'),(3,3,1,1,2,'Chance Eau Tendre','Chaneleeee','2020-09-12 15:11:49'),(4,3,3,3,2,'Miss Dior Blooming Bouquet','The new edition DIOR MISS DIOR BLOOMING BOUQUET has been presented in early January; it has an elegant composition to announce the first days of spring. Its notes provide a subtle and delicate union of floral notes that refresh us and give a precious fragrant trail. Perfumer of the house of Dior - Francois Demanchy describes it as extraordinary silky fragrance created of floral essence of peony refreshed with Sicilian mandarin essences, with a feminine dose provided by rose essence on a velvety base of white musk. Miss Dior Blooming Bouquet is a fragrance that will provide extraordinary elegance of softness – adds Demachy. Peony in the very heart of the composition is supported and shaped with peach and apricot zest accentuating its timeless beauty with velvety and juicy shades. Fragrance bottle Miss Dior Blooming Bouquet takes the same shape as its antecedents and is decorated with a silver ribbon. The perfume is available as 50 ml Eau de Toilette and 100ml Eau de Toilette. Nathalie Portman is cover face of Miss Dior collection and features this advertisement as well as other advertisements of all more recent Miss Dior fragrances. Miss Dior Blooming Bouquet was launched in 2014. The nose behind this fragrance is Francois Demachy.','2020-09-13 09:36:57'),(5,3,2,1,1,'Une Fleur de Chanel','Une Fleur de Chanel by Chanel is a Floral Green fragrance for women. Une Fleur de Chanel launched in 1998. The nose behind this fragrance is Jacques Polge. Top notes are green notes and citruses; middle notes are camellia and jasmine; base note is sandalwood.','2020-09-12 15:30:22');
/*!40000 ALTER TABLE `dbo_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_product_brand`
--

DROP TABLE IF EXISTS `dbo_product_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dbo_product_brand` (
  `brand_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `description` text NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_product_brand`
--

LOCK TABLES `dbo_product_brand` WRITE;
/*!40000 ALTER TABLE `dbo_product_brand` DISABLE KEYS */;
INSERT INTO `dbo_product_brand` VALUES (1,'Chanel','<p><b style=\"font-size: 0.875rem; text-align: justify;\">Chanel S.A. </b><span style=\"font-size: 0.875rem; text-align: justify; font-weight: initial;\">is the fashion house founded in 1910 by Gabrielle Chanel (affectionately called \'Coco\' by friends). Chanel created a real revolution in the fashion world with her simple, elegant fashions of timeless appeal. She was one of the first designers to introduce pants for women, and is widely credited as the creator of fashion staple \"the little black dress.\" Ms. Chanel maintained creative control of the design company until her death in 1971. Karl Lagerfeld took the helm as chief designer in 1983.\n\nChanel created a similar revolution in the world of perfume with its first fragrance, the iconic Chanel N°5, first introduced in 1921.</span><br></p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify; \">This fragrance continues to be a worldwide bestseller, maintaining contemporary appeal with innovative advertising and celebrity endorsements. Chanel N°5 and many of the house\'s oldest enduring fragrances were created by Ernest Beaux. Since 1978 the in-house perfumer for Chanel has been Jacques Polge.\n\nDesigner Chanel has 122 perfumes in our fragrance base. Chanel is an old perfume house. The earliest edition was created in 1920 and the newest is from 2020. Chanel fragrances were made in collaboration with perfumers Jacques Polge, Ernest Beaux, Olivier Polge, Henri Robert, and Christopher Sheldrake.</p>','/img/brand/chanel.jpg','2020-09-13 14:59:12'),(2,'Hugo Boss','<p><b style=\"font-size: 0.875rem;\">Hugo Boss AG</b><span style=\"font-size: 0.875rem; font-weight: initial;\"> is a German fashion and lifestyle house, founded in 1924 by Hugo Boss.\n\nOriginating in Metzingen and still based there today, the company offers multiple lines of fashions and accessories under the core brands Hugo and Boss. The Boss brand offers the following lines: Boss Black, modern classic clothing and the broadest range of products; Boss Orange, more unique and Bohemian in style; Boss Selection, high-end menswear; Boss Green, focusing on sports and active wear. The Hugo brand is known for its fashion-forward, European-influenced styles.</span><br></p><p>\nThe company produces fragrances in conjunction with Proctor &amp; Gamble Prestige. Fragrances for men and women are offered under both the Boss and Hugo brands. The first fragrance was Boss Number One for men, launched in 1985.\n\nDesigner Hugo Boss has 102 perfumes in our fragrance base. The earliest edition was created in 1985 and the newest is from 2020. Hugo Boss fragrances were made in collaboration with perfumers Jean-Marc Chaillan, Pierre Wargnye, Annick Menardo, Ursula Wandel, Will Andrews, Sophie Labbe, Alain Astori, Beatrice Piquet, Calice Becker, Domitille Michalon Bertier, Ilias Ermenidis, Gerard Anthony, Bruno Jovanovic, Pascal Gaurin, Louise Turner, Nathalie Feisthauer, Bob Aliano, Frank Voelkl and Claude Dir.<br></p>','/img/brand/boss.jpg','2020-09-13 15:24:38'),(3,'Christian Dior','<p>Fashion designer Christian Dior was born in France and rose to prominence in the fashion world with the creation of the \"New Look\"—voluminous dresses that emphasized a woman\'s curvy feminine shape.\n\nDior\'s fashion background included working for or alongside other well-known fashion (and perfume) legends of the mid-twentieth century. He entered the fashion business working for Robert Piguet, and later worked at Lucien Lelong with another up-and-coming designer, Pierre Balmain. Dior introduced the \"New Look\" in 1947. It represented a radical departure from the simple, fabric-conserving styles of World War II and was hugely influential in women\'s fashion, as well as helping to re-establish Paris as a fashion capital of the world.\n</p><p>\nThe house of Dior issued perfumes to match its new fashions, launching company Parfums Dior and its first fragrance in 1947: Miss Dior, named for the designer\'s sister Catherine.\n\nChristian Dior passed away in 1957, but the fashion and perfume houses that bear his name live on. The Dior brand name is known worldwide for its haute couture collections, luxurious ready-to-wear fashions, cosmetics and fragrances. Today, Christian Dior is also the main holding company of multi-national luxury goods conglomerate LVMH.\n\nDesigner Christian Dior has 241 perfumes in our fragrance base. </p><p>Christian Dior is an old perfume house. The earliest edition was created in 1947 and the newest is from 2020. Christian Dior fragrances were made in collaboration with perfumers Edmond Roudnitska, Beatrice Piquet, Guy Robert, Paul Vacher, Pierre Bourdon, Maurice Roger, Max Gavarry, Domitille Michalon Bertier, Jacques Cavallier, Jean Martel, Florence Idier, Nathalie Lorson, Francois Demachy, Thierry Wasser, Olivier Polge, Francis Kurkdjian, Jean-Louis Sieuzac, Nejla Barbir, Dominique Ropion, Olivier Cresp, Jean-Pierre Bethouart, Michel Almairac, Bertrand Duchaufour, Louise Turner, Olivier Gillotin, Olivier Pescheux, Nathalie Gracia-Cetto, Carlos Vinals, Calice Becker, Annick Menardo, François Demachy, Jean Carles, Christine Nagel, Edouard Flechier and Carlos Benaim.<br></p>','/img/brand/dior.jpg','2020-09-13 14:59:57'),(4,'Jo Malone London','<p>Jo Malone is a UK-based fragrance designer who got her start in the fragrance industry as a florist. Her lifelong fondness for all things fragrant began as a child, when she tried to produce her own fragrances using flowers from the family garden and grated soap.\n\nWorking as a florist by day and a beautician who did facials by night, her business took off when the Nutmeg and Ginger bath oil she produced as a thank-you gift for clients led to requests for re-orders. In 1994, Jo Malone opened her first boutique in London, offering fragrances, skin care, and home scents. A flagship boutique on London\'s famed Sloane Street was opened in 1999.\n</p><p>\nJo was an early advocate for the concept of combining fragrances. Her unisex scents often focused on a single note or a simple combination of two or three essences. The scents were designed to be worn alone or with other scents in the collection, allowing the wearer to create a custom scent—or a variety of them—by layering the different scents on the skin.\n\nThe simple but elegant cream-colored packaging with black trim perfectly embodied the tasteful, often subtle and dignified scents in the line.\n\nThe success of Jo Malone caught the eye of a beauty industry giant. Estee Lauder Companies acquired the Jo Malone brand in 1999, and Jo remained with the company as its chairwoman and creative director until 2006. Estee Lauder Companies continues to operate and produce new scents under the Jo Malone brand. With the financial backing of Estee Lauder Companies, the Jo Malone brand has expanded its distribution to numerous countries around the world.\n</p><p>\nIn November 2011, designer Jo Malone announced a return to the fragrance industry with the formation of a new brand, Jo Loves, currently available for sale in the UK.\n\nDesigner Jo Malone London has 143 perfumes in our fragrance base. The earliest edition was created in 1990 and the newest is from 2020. Jo Malone London fragrances were made in collaboration with perfumers Jo Malone, Anne Flipo, Fabrice Pellegrin, Yann Vasnier, Christine Nagel, Marie Salamagne, Jean Claude Delville, Beverley Bayne, Mathilde Bijaoui, Lucien Piquet, Patricia Bilodeau, Celine Barel, David Apel, Christian Provenzano, Celine Roux, Pierre Negrin, Serge Majoullier and Louise Turner.<br></p>','/img/brand/jomalone.jpg','2020-09-13 15:00:20'),(5,'Acqua di Parma','<p>Acqua di Parma started as a small factory in Parma. The first fragrance was created in 1916, Colonia, and at the beginning it was used to perfume gentlemen\'s handkerchiefs. Colonia became a real symbol of Italian chic among American and European celebrities in the pre-war (1930\'s) and post-war (1950\'s) years. Cary Grant and David Niven, Ava Gadner and Eva Turner, later joined by Audrey Hepburn, were among the passionate admirers of Colonia. From a small factory, Acqua di Parma grew into a prominent house with wide range distribution. Alas, the frivolous dictator Fashion and unpredictable and unjust Fortune turned their back to this Italian house – the years of great popularity were followed by the years of struggle for survival and against\noblivion.</p><p>\n\nAt the beginning of 1990’s, three influential Italians, Diego Della Valle (Tod\'s s.p.a), Luca Cordero Di Montezemolo (Ferrari) and Paolo Borgomanero (La Perla), united their forces in a very patriotic impetus of preserving Italian classic – to restore Acqua di Parma.\n\nThey are reproducing the old fragrances, starting new projects, such as aromatherapy line Blu Mediterraneo, fragrances for interiors, leather gallantry Travel Collection… Searching for good investments and positioning at the international market, the three gentlemen signed a contract with a luxurious goods giant, LVMH, which in 2003 came to own the complete Acqua di Parma. The gorgeous Italian style specimen was saved and has successfully continued with its development. Once again Hollywood has turned its face to small, but proud, Acqua di Parma – the affection of their famous predecessors has now been passed on to Kevin Costner, Woody Allen, Sharon Stone…\n</p><p>\n \n\nDesigner Acqua di Parma has 74 perfumes in our fragrance base. Acqua di Parma is an old perfume house. The earliest edition was created in 1916 and the newest is from 2020. Acqua di Parma fragrances were made in collaboration with perfumers Bertrand Duchaufour, Jean-Claude Ellena, Francois Demachy, Michel Almairac, Francoise Caron, Francis Kurkdjian, Antoine Maisondieu, Alberto Morillas and Mélanie Carestia.<br></p>','/img/brand/acqua.jpg','2020-09-13 15:00:41'),(6,'Yves Saint Lauren','<p><span style=\"font-size: 0.875rem; font-weight: initial;\">Yves Saint Laurent is the French fashion house founded by Yves Henri Donat Mathieu-Saint-Laurent. The house and designer are often abbreviated as YSL.\n\nLaurent\'s career in fashion began at the tender age of 17, when he served as assistant to Christian Dior. With Dior\'s death four years later, Laurent was named head of the house of Dior at just 21 years of age. Still a young man when the Algerian War of Independence broke out, Laurent was drafted into the army in 1960 and lost his position at Dior. Military service and despair over the loss of his design position resulted in a hospital stay. After a brief period of recuperation, Laurent started his own fashion company and went on to become one of the most influential fashion designers of the 1960s and 1970s.\n\nLaurent became known for his exquisitely-tailored women\'s suits, and is credited with creating the first women\'s tuxedo. The first designer to announce his expansion from couture into ready-to-wear, he is credited with making chic ready-to-wear fashions available to the mass market, and for making ready-to-wear a reputable business for designers. Laurent\'s ready-to-wear line was sold in boutiques named Rive Gauche, which would later be commemorated with a perfume.</span><br></p><p>\n\nYves Saint Laurent remained active as the collection\'s main designer through the late 1980s. After his retirement, a series of designers including Alber Elbaz, Tom Ford and Stefano Pilati have produced collections for the brand. Yves Saint Laurent passed away in 2008.\n\nCosmetics and perfumes have long been included in the designer\'s offerings. The house\'s first fragrance, Y for women, was launched in 1964. Y and several other launches by YSL, including Rive Gauche (1970), Opium (1977), men\'s scent Kourous (1981), and Paris (1983) have become modern classics of the perfume world and remain best-sellers. The company continues to earn new fans with contemporary releases such as Baby Doll (2000), Elle (2007) and La Nuit de l\'Homme (2009). YSL offers fragrances in conjunction with L\'Oreal Group.</p><p>\n\nDesigner Yves Saint Laurent has 223 perfumes in our fragrance base. The earliest edition was created in 1964 and the newest is from 2020. Yves Saint Laurent fragrances were made in collaboration with perfumers Karine Dubreuil, Jean Amic, Ralf Schwieger, Cecile Matton, Nathalie Lorson, Marie Salamagne, Olivier Cresp, Honorine Blanc, Jacques Cavallier, Jean-Claude Ellena, Jean-Francois Latty, Pierre Bourdon, Annick Menardo, Nathalie Feisthauer, Michel Girard, Olivier Pescheux, Anne Flipo, Dominique Ropion, Pierre Wargnye, Juliette Karagueuzoglou, Olivier Polge, Carlos Benaim, Quentin Bisch, Calice Becker, Alberto Morillas, Amandine Clerc-Marie, Fabrice Pellegrin, Julie Masse, Ilias Ermenidis, Carlos Benaïm, Hamid Merati-Kashani, Mathilde Bijaoui, Loc Dong, Harry Fremont, Dora Baghriche, Jean-Louis Sieuzac, Raymond Chaillan, Sophia Grojsman, Laurent Bruyere, Sophie Labbe and Daniela (Roche) Andrier.<br></p>','/img/brand/yves.jpg','2020-09-13 15:01:25'),(7,'Calvin','<p><br></p><p><b>Calvin Klein </b>Inc. is an American fashion brand started by Calvin Klein at the end of the 1960s. The brand is famous for its minimalism and classic style, and helped launch the craze for designer jeans in the 1970s when Klein began adding his name on the back pocket. Today, Calvin Klein is known worldwide for their jeans, casual collections, underwear collections and perfumes. Watches and a jewelry collection became part of the brand\'s offering in 1997. The company\'s \"cK\" logo is as recognizable as its brand name and adorns many products in their line. </p><p>The company\'s first perfume, Calvin, was launched in 1981. Calvin Klein has since launched a series of successful perfumes that, for many, capture the spirit of the decades in which they were introduced, including Obsession (1985 for Women, 1986 for Men), cK One (one of the first fragrances marketed as unisex, in 1994), and Euphoria (2005 for women, 2006 for Men). Klein\'s license for perfumes now belongs to Coty.<br></p>',NULL,'2020-09-13 15:22:56'),(9,'123','<p>123</p>',NULL,'2020-09-13 15:35:39'),(10,'123123','<p>123</p>',NULL,'2020-09-13 15:38:15');
/*!40000 ALTER TABLE `dbo_product_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_product_image`
--

DROP TABLE IF EXISTS `dbo_product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dbo_product_image` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `created_date` date NOT NULL,
  PRIMARY KEY (`image_id`),
  KEY `product_id_idx` (`product_id`),
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `dbo_product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_product_image`
--

LOCK TABLES `dbo_product_image` WRITE;
/*!40000 ALTER TABLE `dbo_product_image` DISABLE KEYS */;
INSERT INTO `dbo_product_image` VALUES (1,1,'images/product/1.jpg','2020-07-21'),(2,1,'images/product/1599932941-images.jpeg','2020-07-21'),(3,1,'images/product/1599932947-sandalo.png','2020-09-13');
/*!40000 ALTER TABLE `dbo_product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_product_scent`
--

DROP TABLE IF EXISTS `dbo_product_scent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dbo_product_scent` (
  `scent_id` tinyint NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`scent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_product_scent`
--

LOCK TABLES `dbo_product_scent` WRITE;
/*!40000 ALTER TABLE `dbo_product_scent` DISABLE KEYS */;
INSERT INTO `dbo_product_scent` VALUES (1,'Fruity'),(2,'Floral'),(3,'Soft Floral'),(4,'Floral Oriental'),(5,'Soft Oriental'),(6,'Oriental'),(7,'Woody Oriental'),(8,'Woody'),(9,'Mossy Wood'),(10,'Dry Wood'),(11,'Aromatic'),(12,'Citrus'),(13,'Water'),(14,'Green');
/*!40000 ALTER TABLE `dbo_product_scent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_product_sku`
--

DROP TABLE IF EXISTS `dbo_product_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dbo_product_sku` (
  `sku_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` int NOT NULL,
  `quantity` int NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `status` tinyint NOT NULL DEFAULT '1',
  `volume` int NOT NULL,
  `created_date` datetime NOT NULL,
  `main_sku` smallint DEFAULT NULL,
  `spec` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`sku_id`),
  KEY `productid_idx` (`product_id`),
  CONSTRAINT `productid` FOREIGN KEY (`product_id`) REFERENCES `dbo_product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_product_sku`
--

LOCK TABLES `dbo_product_sku` WRITE;
/*!40000 ALTER TABLE `dbo_product_sku` DISABLE KEYS */;
INSERT INTO `dbo_product_sku` VALUES (1,1,'0.7 oz/ 20 mL',750,18,'images/product/11.jpg',1,20,'2020-09-12 20:28:26',0,'Spray'),(2,1,'1.7 oz/ 50 mL',1200,10,'images/product/12.jpg',1,50,'2020-07-21 00:00:00',1,'Eau de Cologne Spray'),(3,1,'3.4 oz/ 101 mL',1700,10,'images/product/13.jpg',1,101,'2020-07-21 00:00:00',0,'Eau de Cologne Spray'),(4,2,'0.34 oz/ 10 mL',300,20,'images/product/21.jpg',1,10,'2020-07-21 00:00:00',0,'Eau de Toilette Rollerball'),(5,2,'1 oz / 30 mL',700,19,'images/product/22.jpg',1,30,'2020-07-21 00:00:00',0,'Eau de Toilette'),(6,2,'2.5 oz/ 75 mL',1200,19,'images/product/23.jpg',1,75,'2020-07-21 00:00:00',1,'Eau de Toilette Spray'),(7,2,'5 oz/ 150 mL',1800,20,'images/product/24.jpg',1,150,'2020-07-21 00:00:00',0,'Eau de Toilette Spray'),(8,5,'1.7 oz/ 50 mL',750,15,'images/product/1599922150-une fleur chanel.jpg',1,0,'2020-09-12 21:49:12',1,'Spray'),(9,3,'1.7 oz/ 50 mL',1400,10,'images/product/1599964507-chance-eau-tendre-eau-de-parfum_master.png',1,0,'2020-09-13 09:35:18',1,'Spray'),(10,4,'3.4 oz/ 101 mL',1600,15,'images/product/1599965457-missdior blooming bouquet.jpg',1,0,'2020-09-13 09:51:00',0,'Spray'),(11,4,'1.7 oz/ 50 mL',750,15,'images/product/1599965467-christian-dior-miss-dior-blooming-bouquet-50ml.jpg',1,0,'2020-09-13 09:51:08',1,'Spray');
/*!40000 ALTER TABLE `dbo_product_sku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_product_type`
--

DROP TABLE IF EXISTS `dbo_product_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dbo_product_type` (
  `type_id` tinyint NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_product_type`
--

LOCK TABLES `dbo_product_type` WRITE;
/*!40000 ALTER TABLE `dbo_product_type` DISABLE KEYS */;
INSERT INTO `dbo_product_type` VALUES (1,'Perfume'),(2,'Eau de Perfume'),(3,'Eau de Toilette'),(4,'Eau de Cologne'),(5,'Eau Fraiche');
/*!40000 ALTER TABLE `dbo_product_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_promo_code`
--

DROP TABLE IF EXISTS `dbo_promo_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dbo_promo_code` (
  `promo_code` varchar(45) NOT NULL,
  `discount` float NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `created_date` datetime NOT NULL,
  PRIMARY KEY (`promo_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_promo_code`
--

LOCK TABLES `dbo_promo_code` WRITE;
/*!40000 ALTER TABLE `dbo_promo_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `dbo_promo_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_role`
--

DROP TABLE IF EXISTS `dbo_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dbo_role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_role`
--

LOCK TABLES `dbo_role` WRITE;
/*!40000 ALTER TABLE `dbo_role` DISABLE KEYS */;
INSERT INTO `dbo_role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `dbo_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_tag`
--

DROP TABLE IF EXISTS `dbo_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dbo_tag` (
  `tag_id` int NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(45) NOT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_tag`
--

LOCK TABLES `dbo_tag` WRITE;
/*!40000 ALTER TABLE `dbo_tag` DISABLE KEYS */;
INSERT INTO `dbo_tag` VALUES (1,'perfume'),(2,'party'),(3,'casual'),(4,'ladies'),(5,'gentlemen');
/*!40000 ALTER TABLE `dbo_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_user`
--

DROP TABLE IF EXISTS `dbo_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dbo_user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `gender` tinyint DEFAULT '0',
  `status` tinyint DEFAULT '1',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `phone` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `avatar_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `created_date` datetime NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_user`
--

LOCK TABLES `dbo_user` WRITE;
/*!40000 ALTER TABLE `dbo_user` DISABLE KEYS */;
INSERT INTO `dbo_user` VALUES (2,'tuan2','$2a$10$y/WBRgAdNec4c/qWZfq1wu0F5biFqQ1DJVPUgH9zKCDfv1TRe07.2','tuaan.nn91@gmail.com','',NULL,0,0,'','','','2020-09-14 00:38:34'),(3,'tuantuan2','$2a$10$SxfwAZ2OCHbBlMtqJ.JufuRQVEy8ORA89BogQBn/fPU7g10EJg/0m','tuantuan2@gmail.com','Aptech',NULL,0,0,'Hanoi','','/images/avatar/1599570580-bg_4.jpg','2020-09-08 18:26:19'),(4,'whazza','$2a$10$Qlr90ux70Y6IjRVKvovUbeTf9KJ.AsIvHNOWsdrxC0JlmZ/vUO/wa','testing@gmail.com','',NULL,0,0,NULL,NULL,NULL,'2020-09-10 23:12:45'),(5,'tuantuan123','$2a$10$a/azjrZzuTA1IM9HWYhZ9u/MVoWIVjVmZ4/G4wooVriGDEPnL02Ni','123123@gmail.com',NULL,NULL,0,0,NULL,NULL,NULL,'2020-09-10 23:33:31'),(6,'tuantuan','$2a$10$ewGCS9mFrSkpQI2/i3CuM.Zevw63vKgRJEtwgIN2Okoql7N62IGP.','tuan@gmail.com',NULL,NULL,0,0,NULL,NULL,NULL,'2020-09-10 23:40:27');
/*!40000 ALTER TABLE `dbo_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbo_user_role`
--

DROP TABLE IF EXISTS `dbo_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dbo_user_role` (
  `user_role_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbo_user_role`
--

LOCK TABLES `dbo_user_role` WRITE;
/*!40000 ALTER TABLE `dbo_user_role` DISABLE KEYS */;
INSERT INTO `dbo_user_role` VALUES (24,1,2),(25,2,2),(26,3,2),(27,4,2),(28,5,2),(29,6,2);
/*!40000 ALTER TABLE `dbo_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-15  1:44:38
