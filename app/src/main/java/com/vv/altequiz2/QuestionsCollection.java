package com.vv.altequiz2;

import java.util.ArrayList;
import java.util.List;

public class QuestionsCollection {

    static List<Question> questions = new ArrayList<>();

    private QuestionsCollection() {
        throw new IllegalStateException("Utility class");
    }

    static {
        questions.add(new Question(
                2, " POUR LA BANQUE MONDIALE- LE SEUIL INTERNATIONAL DE PAUVRETÉ EXTRÊME EST FIXÉ À", 3, "A 1-25 dollar par jour ### B 1-90 dollar par jour ### C 5 dollars par jour", "B", -22
        ));

        questions.add(new Question(
                3, "EN 1990- 4 PERSONNES SUR 10 VIVAIENT SOUS LE SEUIL DE PAUVRETÉ EXTRÊME. COMBIEN DE PERSONNES VIVENT ACTUELLEMENT SOUS CE SEUIL?", 3, "A 1 sur 10 ### B 3 sur 10 ### C 5 sur 10", "A", -4
        ));

        questions.add(new Question(
                4, "LA MAJEURE PARTIE DES PERSONNES VIVANT SOUS LE SEUIL DE PAUVRETÉ EXTRÊME VIVENT EN:", 4, "A Amérique latine ### B Asie du Sud ### C Afrique subsaharienne ### D Europe de l’Est", "C", -3
        ));

        questions.add(new Question(
                5, "COMBIEN Y A-T-IL DE PERSONNES SANS-ABRI OU VIVANT DANS DES LOGEMENTS PRÉCAIRES DANS LE MONDE?", 3, "A 1 personne sur 7 000 ### B 1 personne sur 70 ### C 1 personne sur 7", "C", -3
        ));

        questions.add(new Question(
                6, "COMBIEN DE PERSONNES VIVENT DANS UNE SITUATION DE PAUVRETÉ OU D’EXCLUSION SOCIALE EN UNION EUROPÉENNE? millions (soit 23-7 % de la population européenne)", 3, "A 10 millions (soit 1-9 % de la population européenne) ### B 50 millions (soit 9-8 % de la population européenne) ### C 119 ", "C", -2
        ));

        questions.add(new Question(
                7, "QUI EST LE PREMIER EMPLOYEUR MONDIAL?", 3, "A L’industrie textile ### B L’agriculture familiale ### C La téléphonie mobile", "B", -2
        ));

        questions.add(new Question(
                8, "LORS D’UNE CATASTROPHE NATURELLE- QUI EST LE PLUS IMPACTÉ EN TERMES DE BIEN-ÊTRE?", 3, "A Les riches ### B Les pauvres ### C La classe moyenne", "B", -2
        ));

        questions.add(new Question(
                9, " EN 2017- 82 % DE LA RICHESSE MONDIALE CRÉÉE SUR L’ANNÉE A PROFITÉ AUX 1 % LES PLUS RICHES. ET POUR LES 50 % LES PLUS PAUVRES? ", 3, "A Ils n’ont rien récolté ### B Ils ont récolté 5 % de la richesse créée ### C Ils ont récolté 10 % de la richesse créée", "A", -2
        ));

        questions.add(new Question(
                10, "ENTRE 1975 ET 2004- SUR 1556 NOUVEAUX MÉDICAMENTS CRÉÉS- COMBIEN ÉTAIENT DESTINÉS À TRAITER DES MALADIES N’EXISTANT QUE DANS LES PAYS EN DÉVELOPPEMENT? ", 3, "A 21 ### B 156 ### C 345", "A", -2
        ));

        questions.add(new Question(
                11, "EN QUOI L’ACCÈS À L’INFORMATION PEUT-IL AIDER À SORTIR DE LA PRÉCARITÉ?", 4, "A Il favorise la croissance économique et le développement ### B Il permet de se procurer des contenus accessibles et utiles facilement ### C Il facilite les échanges et la communication ### D Toutes ces réponses", "D", -2
        ));

        questions.add(new Question(
                12, "COMBIEN DE PERSONNES SOUFFRENT DE LA FAIM DANS LE MONDE? ", 3, "A 1 sur 3 ### B 1 sur 10 ### C 1 sur 20", "B", -2
        ));

        questions.add(new Question(
                13, "VRAI OU FAUX? DEPUIS 1990- LE NOMBRE DE PERSONNES QUI SOUFFRENT DE LA FAIM DANS LE MONDE A DIMINUÉ.", 2, "A Vrai ### B Faux", "A", -1
        ));

        questions.add(new Question(
                14, "PARMI LES 815 MILLIONS DE PERSONNES TOUCHÉES PAR LA FAIM- LA MOITIÉ SONT:", 3, "A Des femmes ### B Des paysans ### C Des personnes au régime", "B", -1
        ));

        questions.add(new Question(
                15, "EST-CE QU’ON PRODUIT ASSEZ DE NOURRITURE POUR NOURRIR TOUT LE MONDE? ", 3, "A Non- il faut produire plus ### B Oui- mais elle est inégalement répartie et tout le monde n’y a pas accès ### C Ça dépend des années", "B", -1
        ));

        questions.add(new Question(
                16, "POUR NOURRIR LE MONDE EN 2050- LA PRODUCTION DE NOURRITURE DOIT AUGMENTER DE: ", 3, "A 20 % ### B 40 % ### C 70 %", "C", -1
        ));

        questions.add(new Question(
                17, "LE PRIX DES PRODUITS ALIMENTAIRES A-T-IL AUGMENTÉ DEPUIS LES ANNÉES 2000? ", 3, "A Non- il a diminué ### B Oui- il a presque doublé ### C Il est stable", "B", -1
        ));

        questions.add(new Question(
                18, "COMBIEN D’ALIMENTS SONT GASPILLÉS DANS LE MONDE (PAR SECONDE)?", 3, "A 41 kg ### B 410 kg ### C 41 tonnes", "C", -1
        ));

        questions.add(new Question(
                19, "QU’EST-CE QUE L’INSÉCURITÉ ALIMENTAIRE?", 3, "A Lorsque tu souffres ou risques de souffrir de la faim ### B Lorsque tu ne manges pas 5 fruits et légumes par jour ### C Lorsque tu risques ta vie pour trouver de la nourriture", "A", -1
        ));

        questions.add(new Question(
                20, "QUELLE EST LA PÉRIODE LA PLUS CRITIQUE ET DANGEREUSE EN CAS DE MALNUTRITION D’UN ENFANT?", 3, "A Entre -9 mois (conception) et 24 mois ### B Entre 2 et 4 ans ### C Entre 4 et 8 ans", "A", -1
        ));

        questions.add(new Question(
                21, "L’AGRICULTURE FAMILIALE EMPLOIE-T-ELLE?", 3, "A 15 % de la population active mondiale ### B 42 % de la population active mondiale ### C 70 % de la population active mondiale", "B", -1
        ));

        questions.add(new Question(
                22, "QUELLE EST L’ESPÉRANCE DE VIE MOYENNE DANS LE MONDE? A 51 ans B 61 ans C 71 ans", 3, "A 51 ans ### B 61 ans ### C 71 ans", "C", -1
        ));

        questions.add(new Question(
                23, "POURQUOI EST-IL IMPORTANT DE FAIRE DU SPORT TOUS LES JOURS? ", 4, "A Cela réduit le risque de maladies chroniques et cardiovasculaires ### B Cela permet d’avoir une bonne condition physique ### C Cela améliore le sommeil et réduit le stress et l’anxiété ### D Toutes ces réponses", "D", -1
        ));

        questions.add(new Question(
                24, "COMBIEN DE TEMPS UN ENFANT ENTRE 5 ET 17 ANS DEVRAIT-IL FAIRE UNE ACTIVITÉ PHYSIQUE CHAQUE JOUR?", 3, "A 30 minutes ### B 1 heure ### C 2 heures", "B", -1
        ));

        questions.add(new Question(
                25, "QUEL CONTINENT COMPTE 90 % DES CAS DE PALUDISME?", 3, "A L’Asie ### B L’Afrique ### C L’Amérique latine", "B", -1
        ));

        questions.add(new Question(
                26, "QUELLE EST LA PRINCIPALE CAUSE DE MORTALITÉ DANS LE MONDE?", 3, "A L’insalubrité de l’environnement ### B Les attaques de requins ### C Les accidents de la route", "A", -1
        ));

        questions.add(new Question(
                27, "QUELLE EST LA PRINCIPALE CAUSE DE MORTALITÉ INFANTILE DANS LES PAYS EN DÉVELOPPEMENT?", 3, "A La guerre ### B La malnutrition ### C Les maladies infectieuses", "B", 0
        ));

        questions.add(new Question(
                28, "L’IMPACT DU CHANGEMENT CLIMATIQUE SUR LA SANTÉ SERAIT-IL PLUTÔT NÉGATIF OU POSITIF DANS L’ENSEMBLE? ", 2, "A Positif: le changement climatique permettra de rendre les zones froides plus vivables- et les zones trop humides plus sèches ### B Négatif: dans la plupart des pays- le changement climatique aura une mauvaise influence sur la qualité de l’air- de l’eau- la quantité de la nourriture et la sécurité du logement", "B", 0
        ));

        questions.add(new Question(
                29, "QUELLES POPULATIONS SERONT LE PLUS EXPOSÉES AUX RISQUES SANITAIRES RÉSULTANTS DU CHANGEMENT CLIMATIQUE? CELLES QUI VIVENT: ", 6, "A Dans de petits états insulaires en développement ### B Dans des régions côtières ### C Dans les mégapoles ### D Dans les régions montagneuses ### E Dans les zones polaires ### F Toutes ces réponses", "F", 0
        ));

        questions.add(new Question(
                30, "VRAI OU FAUX? AUJOURD’HUI L’OBÉSITÉ TUE TROIS FOIS PLUS QUE LA MALNUTRITION. ", 2, "A Vrai ### B Faux", "A", 0
        ));

        questions.add(new Question(
                31, "LE JAPON ET LA SIERRA LEONE SONT LES DEUX EXTRÊMES D’UN INDICATEUR DE BIEN-ÊTRE. LEQUEL? ", 3, "A Le taux de chômage: 3-1 % au Japon et 60 % en Sierra Leone ### B Le taux d’alphabétisation: 99 % au Japon et 42 % en Sierra Leone ### C L’espérance de vie: 84 ans au Japon et 50 ans en Sierra Leone soit 34 années d’écart", "C", 0
        ));

        questions.add(new Question(
                32, "COMBIEN D’ADULTES NE SAVENT NI LIRE NI ÉCRIRE DANS LE MONDE?", 3, "A 2 sur 10 ### B 3 sur 10 ### C 4 sur 10", "A", 0
        ));

        questions.add(new Question(
                33, "DANS LE MONDE- DEPUIS 2000- LE NOMBRE D’ÉTUDIANTS INSCRITS DANS DES ÉTABLISSEMENTS D’ÉTUDES SUPÉRIEURES:", 3, "A A baissé ### B A doublé ### C Est resté stable", "B", 0
        ));

        questions.add(new Question(
                34, "OÙ VIVENT 50 % DES ENFANTS QUI NE SONT PAS INSCRITS À L’ÉCOLE?", 4, "A Europe de l’Est ### B Asie du Sud ### C Amérique du Nord ### D Afrique subsaharienne", "D", 0
        ));

        questions.add(new Question(
                35, "DANS LES PAYS EN DÉVELOPPEMENT- COMBIEN D’ENFANTS VONT À L’ÉCOLE LE VENTRE VIDE? ", 3, "A 510 000 enfants (= population de Lyon) ### B 10 millions d’enfants (= population de l’Île-de-France) ### C 66 millions d’enfants (= population de la France)", "C", 0
        ));

        questions.add(new Question(
                36, "DEPUIS 1990- LA SCOLARISATION DES JEUNES ENFANTS (3 À 6 ANS) A AUGMENTÉ DE: ", 3, "A 10 % ### B 20 % ### C 30 %", "C", 0
        ));

        questions.add(new Question(
                37, "VRAI OU FAUX? L’UN DES PRINCIPAUX OBJECTIFS DE L’ODD 4 EST DE POUVOIR GARANTIR UNE ÉDUCATION GRATUITE POUR TOUS.", 2, "A Vrai ### B Faux", "A", 0
        ));

        questions.add(new Question(
                38, "EN MOYENNE- QUELLE PART DU BUDGET ÉDUCATION DANS LE MONDE EST DÉDIÉE À LA PETITE ENFANCE?", 3, "A 2 % ### B 10 % ### C 20 %", "A", 0
        ));

        questions.add(new Question(
                39, "LA TRANSMISSION DES NOTIONS D’ÉGALITÉ ET DE JUSTICE POUR TOUS DOIT SE FAIRE DÈS: ", 4, "A L’école primaire ### B Le collège ### C Le lycée ### D Les études supérieures", "A", 0
        ));

        questions.add(new Question(
                40, "DANS LE MONDE- COMBIEN D’ENFANTS ET DE JEUNES SONT CONFRONTÉS À DES VIOLENCES OU DU HARCÈLEMENT À L’ÉCOLE?", 3, "A 1 enfant sur 100 ### B 1 enfant sur 30 ### C 1 enfant sur 15", "C", 0
        ));

        questions.add(new Question(
                41, "DANS LE MONDE- QUELLE EST LA PART DES ENFANTS CONTRAINTS DE TRAVAILLER?", 3, "A 1 % ### B 5 % ### C 11 %", "C", 0
        ));

        questions.add(new Question(
                42, "QUELLE PART DES PARLEMENTAIRES DANS LE MONDE SONT DES FEMMES?", 3, "A 10 % ### B 23 % ### C 51 %", "B", 0
        ));

        questions.add(new Question(
                43, "SUR LES 815 MILLIONS DE PERSONNES NE SACHANT NI LIRE NI ÉCRIRE DANS LE MONDE- COMBIEN SONT DES FEMMES?", 3, "A Un tiers ### B La moitié ### C Deux tiers", "C", 0
        ));

        questions.add(new Question(
                44, "SEULS DEUX PAYS DANS LE MONDE ONT PLUS DE FEMMES QUE D’HOMMES QUI SIÈGENT AU PARLEMENT. LESQUELS? ", 5, "A Le Royaume-Uni ### B La Norvège ### C La France ### D Le Rwandae ### E La Bolivie", "D-E", 0
        ));

        questions.add(new Question(
                45, "QUEL PAYS A ÉTÉ LE PREMIER AU MONDE À DONNER AUX FEMMES LE DROIT DE VOTE ET D’ÊTRE CANDIDATES? ", 3, "A La France ### B Les États-Unis ### C La Finlande", "C", 0
        ));

        questions.add(new Question(
                46, "COMBIEN DE PAYS DANS LE MONDE ONT UNE FEMME COMME PRÉSIDENT OU PREMIER MINISTRE?", 3, "A 5 ### B 16 ### C 31", "B", 1
        ));

        questions.add(new Question(
                47, "LESQUELLES DE CES AFFIRMATIONS SONT EXACTES? ", 4, "A À travail égal- l’homme gagne 20 % de plus que la femme ### B Dans le monde- les femmes travaillent en moyenne quatre ans de plus que les hommes ### C Naturellement- c’est mieux que les femmes éduquent les enfants ### D Génétiquement- les femmes ont moins l’esprit scientifique que les hommes", "A-B", 1
        ));

        questions.add(new Question(
                48, "QUEL EST LE POURCENTAGE DE FEMMES PARMI LES CHERCHEURS?", 3, "A 29 % ### B 49 % ### C 72 %", "A", 1
        ));

        questions.add(new Question(
                49, "QUELS SONT LES TROIS PAYS SE DISTINGUANT PAR L’AMÉLIORATION DE L’ÉGALITÉ ENTRE HOMMES ET FEMMES EN 2017?", 3, "A Le Portugal- les États-Unis et l’Allemagne ### B Le Rwanda- le Nicaragua et la Slovénie ### C La Grèce- l’Italie et Chypre", "B", 1
        ));

        questions.add(new Question(
                50, "SI 75 % DES HOMMES SONT ACTIFS (AYANT UN EMPLOI OU EN RECHERCHE)- QUELLE EST LA PART DE FEMMES ACTIVES DANS LE MONDE?", 3, "A 25 % ### B 48-5 % ### C 75 %", "B", 1
        ));

        questions.add(new Question(
                51, "D’APRÈS LE FORUM ÉCONOMIQUE MONDIAL- LES INÉGALITÉS HOMMES/FEMMES AU TRAVAIL NE DISPARAÎTRONT PAS AVANT: ", 3, "A 2030 ### B 2100 ### C 2234", "C", 1
        ));

        questions.add(new Question(
                52, "PARMI LES PROPOSITIONS SUIVANTES- LAQUELLE EST LA PLUS DANGEREUSE POUR L’HOMME?", 3, "A Le requin ### B Le moustique ### C L’eau non potable", "C", 1
        ));

        questions.add(new Question(
                53, "QUELLE QUANTITÉ D’EAU EST NÉCESSAIRE POUR SURVIVRE?", 3, "A 2 à 5 litres par jour ### B 8 à 15 litres par jour ### C 40 à 60 litres par jour", "B", 1
        ));

        questions.add(new Question(
                54, "QUEL EST LE POURCENTAGE D’EAU DOUCE DANS LE MONDE?", 3, "A Moins de 3 % ### B 50 % ### C Plus de 95 %", "A", 1
        ));

        questions.add(new Question(
                55, "POUR ÉCONOMISER L’EAU AU QUOTIDIEN- IL EST PRÉFÉRABLE DE: ", 3, "A Faire la vaisselle à la main plutôt qu’utiliser un lave-vaisselle ### B Prendre de courtes douches plutôt que des bains ### C Boire des sodas plutôt que de l’eau", "B", 1
        ));

        questions.add(new Question(
                56, "OÙ PARTENT 90 % DES EAUX USÉES PROVENANT DES ACTIVITÉS HUMAINES?", 3, "A Elles sont traitées et recyclées ### B Elles sont conservées en lieu sûr dans des bassins étanches ### C Elles sont déversées dans les rivières ou les mers sans avoir été traitées", "C", 1
        ));

        questions.add(new Question(
                57, "DANS LE MONDE- COMBIEN DE PERSONNES N’ONT PAS ACCÈS À DES TOILETTES PROPRES ET SAINES? ", 3, "A 1 personne sur 100 ### B 1 personne sur 10 ### C 1 personne sur 3", "C", 1
        ));

        questions.add(new Question(
                58, "QU’EST-CE QUE L’HYDROÉLECTRICITÉ?", 3, "A L’électricité produite par l’énergie des rivières- des fleuves ou des lacs ### B L’électricité produite par l’énergie des vagues ### C L’électricité produite par le ruissellement des eaux de pluie", "A", 1
        ));

        questions.add(new Question(
                59, "QU’EST-CE QUE LE STRESS HYDRIQUE?", 3, "A Des ressources insuffisantes pour répondre aux besoins en eau ### B Ne pas savoir nager ### C La peur de l’eau", "A", 1
        ));

        questions.add(new Question(
                60, "LA DÉCENNIE INTERNATIONALE DE L’EAU POTABLE ET DE L’ASSAINISSEMENT- DÉCRÉTÉE PAR L’ONU- A NOTAMMENT PERMIS À:", 3, "A 130 000 personnes d’avoir accès à l’eau douce ### B 1 300 000 personnes d’avoir accès à l’eau douce ### C 1-3 milliard de personnes d’avoir accès à l’eau douce", "C", 1
        ));

        questions.add(new Question(
                61, "QUEL EST LE PREMIER USAGE DE L’EAU PRÉLEVÉE DANS LES LACS- LES RIVIÈRES ET LES AQUIFÈRES?", 3, "A Se laver ### B Irriguer les cultures ### C Fabriquer des vêtements", "B", 1
        ));

        questions.add(new Question(
                62, "L’HOMME GÉNÈRE ENTRE: ", 3, "A 1 à 40 tonnes de déchets par seconde (l’équivalent d’un poids lourd rempli) ### B 41 à 79 tonnes de déchets par seconde ### C 80 à 130 tonnes de déchets par seconde (une baleine bleue pèse entre 80 et 200 tonnes)", "C", 1
        ));

        questions.add(new Question(
                63, "PEUT-ON TRANSFORMER CES DÉCHETS EN ÉNERGIE?", 2, "A Oui ### B Non ", "A", 1
        ));

        questions.add(new Question(
                64, "TROUVEZ LES INTRUS. L’ÉNERGIE NON POLLUANTE PEUT ÊTRE PRODUITE AVEC: ", 6, "A Le nucléaire ### B Le soleil ### C Le pétrole ### D Le vent ### E Les vagues ### F Le charbon ", "A-C-F", 1
        ));

        questions.add(new Question(
                65, "CERTAINES ÉNERGIES SONT DITES RENOUVELABLES. POURQUOI?", 3, "A Parce qu’elles ne polluent pas ### B Parce qu’elles sont nouvelles ### C Parce qu’elles peuvent", "C", 1
        ));

        questions.add(new Question(
                66, "QUELLE EST L’ÉNERGIE RENOUVELABLE LA PLUS UTILISÉE DANS LE MONDE: ", 3, "A L’énergie solaire ### B L’énergie hydraulique ### C L’énergie éolienne", "B", 1
        ));

        questions.add(new Question(
                67, "QUE SIGNIFIE « EFFICACITÉ ÉNERGÉTIQUE » ?", 3, "A Lorsque la lumière d’une lampe est très vive ### B Lorsque l’on peut utiliser l’électricité sans s’inquiéter de sa consommation ### C Lorsqu’un appareil ou un bâtiment utilise relativement peu d’énergie pour fonctionner", "C", 1
        ));

        questions.add(new Question(
                68, "COMBIEN DE PERSONNES N’ONT PAS ACCÈS À L’ÉLECTRICITÉ DANS LE MONDE?", 3, "A 800 millions (soit la population de l’Europe) ### B 1-3 milliard (soit la population de la Chine) ### C 3 milliards (soit la population de la Chine- de l’Inde et des États-Unis réunis)", "B", 1
        ));

        questions.add(new Question(
                69, "LES GAZ À EFFET DE SERRE SONT MULTIPLES. PARMI CETTE LISTE- LEQUEL N’EN EST PAS UN?", 5, "A La vapeur d’eau ### B L’oxygène ### C Le méthane ### D Le dioxyde de carbone ### E Le protoxyde d’azote ou gaz hilarant", "B", 1
        ));

        questions.add(new Question(
                70, "EN 2015- QUEL PAYS A LE PLUS INVESTI DANS LES ÉNERGIES RENOUVELABLES?", 3, "A Le Japon- dont les énergies renouvelables représentent 7 % de son mix énergétique ### B Les États-Unis- avec 44 milliards de dollars investis dans les énergies renouvelables ### C La Chine- à hauteur de 36 % des investissements mondiaux dans les énergies renouvelables", "C", 1
        ));

        questions.add(new Question(
                71, "QUEL PROCÉDÉ PEUT-ÊTRE UTILISÉ POUR VALORISER DES DÉCHETS?", 4, "A Le compostage ### B La méthanisation ### C Le recyclage ### D L’enfouissement", "A-B-C", 1
        ));

        questions.add(new Question(
                72, "QU’EST-CE QU’UNE CROISSANCE ÉCONOMIQUE DURABLE ENTEND PRÉSERVER? ", 3, "A La hausse du cours de la Bourse ### B L’environnement ### C Le travail décent pour les enfants", "B", 1
        ));

        questions.add(new Question(
                73, "LE « TRAVAIL DÉCENT » REVÊT PLUSIEURS CRITÈRES. LESQUELS? ", 5, "A Le droit de ne pas travailler ### B La sécurité sur le lieu de travail ### C La protection sociale pour les familles ### D Un traitement inégal entre femmes et hommes ### E Une rémunération convenable", "B-C-E", 1
        ));

        questions.add(new Question(
                74, "COMBIEN DE PERSONNES SE TROUVENT EN SITUATION DE CHÔMAGE DANS LE MONDE?", 3, "A 20 millions (soit 0-3 % de la population mondiale) ### B 200 millions (soit 3 % de la population mondiale) ### C 2 milliards (soit 30 % de la population mondiale)", "B", 1
        ));

        questions.add(new Question(
                75, "DANS LE MONDE- 168 MILLIONS D’ENFANTS DE 5 À 17 ANS TRAVAILLENT. QUELLE PART D’ENTRE EUX TRAVAILLE DANS LE SECTEUR AGRICOLE?", 3, "A 20 % ### B 40 % ### C 60 %", "C", 1
        ));

        questions.add(new Question(
                76, "VRAI OU FAUX? LES FLUX DE MIGRANTS INTERNATIONAUX ONT UN IMPACT POSITIF SUR L’ÉCONOMIE MONDIALE.", 2, "A Vrai ### B Faux", "A", 1
        ));

        questions.add(new Question(
                77, "POUR SUIVRE LA CROISSANCE DE LA POPULATION MONDIALE EN ÂGE DE TRAVAILLER- COMBIEN FAUT-IL CRÉER D’EMPLOIS PAR AN?", 3, "A 3 millions ### B 30 millions ### C 300 millions", "B", 2
        ));

        questions.add(new Question(
                78, "QUEL EST LE LIEN ENTRE L’EMPLOI ET LE CHANGEMENT CLIMATIQUE?", 3, "A La transition vers une économie sobre en carbone menace plusieurs domaines- tels que l’agriculture- le secteur de l’automobile ou des énergies fossiles- etc. ### B L’économie verte fait émerger des nouvelles professions et crée de nouveaux emplois ### C Ces deux phrases sont vraies", "C", 2
        ));

        questions.add(new Question(
                79, "TOUTES LES 15 SECONDES- UN TRAVAILLEUR MEURT D’UN ACCIDENT OU D’UNE MALADIE LIÉS AU TRAVAIL. QUELLE EST LA PRINCIPALE CAUSE DE CES DÉCÈS? ", 3, "A Accident sur le trajet (domicile/travail) ### B Maladie professionnelle ### C Accident ou erreur durant le travail", "B", 2
        ));

        questions.add(new Question(
                80, "DANS LE MONDE- LE TAUX D’EMPLOI DES PERSONNES VIVANT AVEC UN HANDICAP EST:", 3, "A Plus élevé que celui des personnes non handicapées ### B Plus faible que celui des personnes non handicapées ### C Équivalent à celui des personnes non handicapées", "B", 2
        ));

        questions.add(new Question(
                81, "PARMI LES 197 PAYS RECONNUS PAR L’ONU- COMBIEN ONT UNE LÉGISLATION ANTIDISCRIMINATOIRE OU FAISANT SPÉCIFIQUEMENT RÉFÉRENCE AUX HANDICAPÉS?", 3, "A 45 pays ### B 97 pays ### C 139 pays", "A", 2
        ));

        questions.add(new Question(
                82, "QUELLE EST LA VILLE LA PLUS « VERTE » DU MONDE? ", 3, "A Reykjavik (Islande) ### B Montréal (Canada) ### C Paris (France)", "A", 2
        ));

        questions.add(new Question(
                83, "VRAI OU FAUX? L’ARROSAGE CLASSIQUE EST PLUS ÉCONOMIQUE ET RESPONSABLE QUE L’ARROSAGE EN GOUTTE À GOUTTE.", 2, "A Vrai ### B Faux", "B", 2
        ));

        questions.add(new Question(
                84, "LAQUELLE DE CES INVENTIONS EST LA MEILLEURE AFIN DE LUTTER CONTRE LE CHANGEMENT CLIMATIQUE? ", 4, "A La voiture électrique ### B La voiture diesel ### C La voiture essence ### D La bicyclette", "D", 2
        ));

        questions.add(new Question(
                85, "DANS LES PAYS LES MOINS AVANCÉS- QUELLE EST LA PART DE LA POPULATION QUI N’A PAS ACCÈS À INTERNET? ", 3, "A 95 % ### B 70 % ### C 50 %", "A", 2
        ));

        questions.add(new Question(
                86, "VRAI OU FAUX? AUJOURD’HUI- ENTRE 1 MILLIARD ET 1-5 MILLIARD DE PERSONNES N’ONT PAS ACCÈS À DES SERVICES DE TÉLÉCOMMUNICATIONS FIABLES? ", 2, "A Vrai ### B Faux", "A", 2
        ));

        questions.add(new Question(
                87, "QUELLES SONT LES CARACTÉRISTIQUES D’UNE ENTREPRISE DURABLE?le respect de l’environnement D Elle produit des biens et services qui durent dans le temps", 3, "A Elle fonctionne dans une logique de développement durable ### B Elle fonctionne depuis longtemps ### C Elle prend en compte ", "A-C", 2
        ));

        questions.add(new Question(
                88, "D’ICI 2030- LE SECTEUR DES ÉNERGIES RENOUVELABLES POURRAIT CRÉER:", 3, "A 5 000 emplois ### B 5 millions d’emplois ### C 20 millions d’emplois", "C", 2
        ));

        questions.add(new Question(
                89, "QU’EST-CE QUE L’ÉCO-CONCEPTION?", 3, "A Concevoir en fonction de l’économie ### B Intégrer l’environnement dès la conception d’un projet ou d’un produit ### C Réaliser le plus d’économie et réduire les coûts", "B", 2
        ));

        questions.add(new Question(
                90, "TROUVEZ L’INTRUS. QUEL EST L’INTÉRÊT D’INTÉGRER L’ÉCO-CONCEPTION DANS UN PROJET?", 4, "A Réduire l’impact sur l’environnement ### B Réduire les profits de l’entreprise ### C Optimiser un projet ou produit ### D Permettre de faire des économies", "B", 2
        ));

        questions.add(new Question(
                91, "DANS LES PAYS À FAIBLE REVENU- LES CONTRAINTES EN MATIÈRE D’INFRASTRUCTURES AFFECTENT LA PRODUCTIVITÉ DES ENTREPRISES: ", 2, "A De moins de 10 % ### B De presque 40 %", "B", 2
        ));

        questions.add(new Question(
                92, "L’INÉGALITÉ DES REVENUS :", 3, "A Augmente la croissance économique et la pauvreté ### B Diminue la pauvreté et la croissance économique ### C Diminue la croissance économique et augmente la pauvreté", "C", 2
        ));

        questions.add(new Question(
                93, "VRAI OU FAUX? 75 % DE LA POPULATION VIT DANS DES SOCIÉTÉS OÙ LES REVENUS SONT DISTRIBUÉS DE FAÇON PLUS INÉGALE QUE DANS LES ANNÉES 1990. ", 2, "A Vrai ### B Faux", "A", 2
        ));

        questions.add(new Question(
                94, "SI TOUS LES HUMAINS VIVAIENT COMME LES FRANÇAIS- COMBIEN DE PLANÈTES TERRE SERAIENT NÉCESSAIRES POUR SATISFAIRE LEURS BESOINS?", 3, "A 2 planètes ### B 3 planètes ### C 6 planètes", "B", 2
        ));

        questions.add(new Question(
                95, "PARMI LES PERSONNES LES PLUS PAUVRES DANS LE MONDE- COMBIEN BÉNÉFICIENT D’UNE PROTECTION SOCIALE? ", 3, "A Tous ### B La moitié ### C Un tiers", "C", 2
        ));

        questions.add(new Question(
                96, "VIA LA MONDIALISATION DES ÉCHANGES ET DE LA PRODUCTION- LES INÉGALITÉS ENTRE PAYS DÉVELOPPÉS ET EN DÉVELOPPEMENT: ", 3, "A Baissent puisqu’on délocalise dans les pays en développement ### B Augmentent car les profits sont concentrés dans les pays développés ### C Sont vouées à disparaître naturellement par la loi du marché mondial", "B", 2
        ));

        questions.add(new Question(
                97, "L’AFRIQUE PRODUIT 75 % DU CACAO MONDIAL. QUEL POURCENTAGE PERÇOIT-ELLE DES REVENUS DE SA FABRICATION DE CHOCOLAT?", 3, "A 2 % ### B 45 % ### C 75 %", "A", 2
        ));

        questions.add(new Question(
                98, "UN VOYAGE CLASSIQUE PARIS-LONDRES COÛTE EN MOYENNE 150 €. COMBIEN COÛTE UN PARIS-LONDRES POUR UN MIGRANT?", 3, "A 5 € ### B 150 € ### C 10 000 €", "C", 2
        ));

        questions.add(new Question(
                99, "DANS QUELLE PROPORTION LES INÉGALITÉS DE REVENUS ENTRE LES PLUS RICHES ET LES PLUS PAUVRES ONT-ELLES AUGMENTÉ DANS LES PAYS EN DÉVELOPPEMENT ENTRE 1990 ET 2010? ", 3, "A 11 % ### B 25 % ### C 42 %", "A", 2
        ));

        questions.add(new Question(
                100, "LES FEMMES EN ZONE URBAINE ONT:", 3, "A 2 fois plus de risque de mourir pendant l’accouchement que celles qui vivent dans des zones rurales ### B 3 fois moins de risque de mourir pendant l’accouchement que celles qui vivent dans des zones rurales ### C Autant de risque de mourir pendant l’accouchement que celles qui vivent dans des zones rurales", "B", 2
        ));

        questions.add(new Question(
                101, "À L’ÉCHELLE MONDIALE- QUELLE PART DES TRAVAILLEURS DOMESTIQUES SONT DES FEMMES?", 3, "A 34 % ### B 68 % ### C 83 %", "C", 2
        ));

        questions.add(new Question(
                102, "POUR UN TRAJET DE 10 KM EN VILLE- QUEL EST LE MODE DE TRANSPORT LE  MOINS POLLUANT. ", 6, "A Tramway ### B Moto ### C Bus ### D Voiture ### E Vélo ### F Métro", "E", 2
        ));

        questions.add(new Question(
                103, "EN 1990- 10 VILLES DANS LE MONDE COMPTAIENT 10 MILLIONS D’HABITANTS OU PLUS. EN 2018- ON EN COMPTAIT : ", 3, "A 10 ### B 21 ### C 47", "C", 2
        ));

        questions.add(new Question(
                104, "95 % DE L’EXPANSION URBAINE AURA LIEU DANS: ", 2, "A Les pays développés ### B Les pays en développement", "B", 2
        ));

        questions.add(new Question(
                105, "QUELLE PART DE LA POPULATION MONDIALE HABITE EN ZONE URBAINE AUJOURD’HUI? ", 3, "A 54 % de la population mondiale ### B 20 % de la population mondiale ### C 3 milliards- soit 35 % de la population mondiale", "C", 3
        ));

        questions.add(new Question(
                106, "LES VILLES COUVRENT 3 % DE LA SUPERFICIE TERRESTRE. POUR AUTANT- QUELLE EST LEUR PART DES ÉMISSIONS MONDIALES DE DIOXYDE DE CARBONE?", 3, "A 3 % des émissions de dioxyde de carbone ### B 50 % des émissions de dioxyde de carbone ### C 70 % des émissions de dioxyde de carbone", "C", 3
        ));

        questions.add(new Question(
                107, "DANS LE MONDE- COMBIEN DE PERSONNES VIVENT DANS UN BIDONVILLE ?", 3, "A 75 millions- soit 1 être humain sur 100 ### B 900 millions- soit 1 être humain sur 8 ### C 3 milliards- soit 2 êtres humains sur 5", "B", 3
        ));

        questions.add(new Question(
                108, "D’ICI 2030- SI RIEN N’EST FAIT- COMBIEN DE PERSONNES VIVRONT DANS DES BIDONVILLES?", 3, "A 75 millions- soit 1 être humain sur 100 ### B 900 millions- soit 1 être humain sur 8 ### C 3 milliards- soit 2 êtres humains sur 5", "C", 3
        ));

        questions.add(new Question(
                109, "TROUVEZ L’INTRUS. L’URBANISATION RAPIDE EXERCE UNE PRESSION SUR: ", 4, "A Les réserves d’eau douce ### B La croissance économique ### C Les systèmes d’approvisionnement en eau ### D Le cadre de vie ### E La santé publique", "B", 3
        ));

        questions.add(new Question(
                110, "UNE VILLE « RÉSILIENTE » EST…", 3, "A Une ville réticente au changement ### B Une ville capable de résister et de s’adapter aux perturbations qu’elle subit ### C Une ville qui change de fournisseur en eau- électricité ou gaz", "B", 3
        ));

        questions.add(new Question(
                111, "QU’EST-CE QUE L’HORTICULTURE URBAINE ET PÉRIURBAINE?", 3, "A La culture de plantes et l’élevage d’animaux à l’intérieur et aux abords des villes ### B La culture d’ortie qui est un trésor de bienfaits pour la santé ### C Une thérapie relaxante", "A", 3
        ));

        questions.add(new Question(
                112, "QUELLE PART DE LA NOURRITURE MONDIALE EST GÂCHÉE OU JETÉE?", 3, "A 1/4 de la nourriture produite ### B 1/3 de la nourriture produite ### C 1/2 de la nourriture produite", "B", 3
        ));

        questions.add(new Question(
                113, "CHAQUE ANNÉE- COMBIEN DE M3 D’EAU UTILISET-ON INUTILEMENT POUR PRODUIRE DES DENRÉES GASPILLÉES?", 3, "A 250 m3 ### B 25 km3 ### C 250 km3", "C", 3
        ));

        questions.add(new Question(
                114, "LA SURCONSOMMATION DE NOURRITURE NUIT À: ", 5, "A Votre portefeuille ### B L’environnement ### C Votre santé ### D Un partage équitable des ressources alimentaires ### E Toutes ces réponses", "E", 3
        ));

        questions.add(new Question(
                115, "EN 2017- À QUELLE DATE L’HOMME AVAIT-IL CONSOMMÉ PLUS DE RESSOURCES NATURELLES QUE LA PLANÈTE EST EN CAPACITÉ D’EN PRODUIRE? ", 3, "A 2 août ### B 10 octobre ### C 22 novembre", "A", 3
        ));

        questions.add(new Question(
                116, "SI LES HABITANTS DU MONDE ENTIER SE METTAIENT À UTILISER DES AMPOULES À HAUT RENDEMENT ÉNERGÉTIQUE- ON ÉCONOMISERAIT: ", 3, "A 12 millions de dollars chaque année ### B 120 millions de dollars chaque année ### C 120 milliards de dollars chaque année", "C", 3
        ));

        questions.add(new Question(
                117, "QUEL PAYS A ÉTÉ LE PREMIER À INTERDIRE LES SACS PLASTIQUES?", 3, "A Le Danemark ### B Le Bangladesh ### C L’Irlande", "B", 3
        ));

        questions.add(new Question(
                118, "SI LES INSECTES POLLINISATEURS- COMME LES ABEILLES- VENAIENT À DISPARAÎTRE- COMBIEN CELA COÛTERAIT-IL DE FAIRE LEUR TRAVAIL?", 3, "A 153 000 euros ### B 153 millions d’euros ### C 153 milliards d’euros", "C", 3
        ));

        questions.add(new Question(
                119, "PARMI LES PROPOSITIONS SUIVANTES- QUEL EST LE SECTEUR QUI ÉMET LE PLUS DE GAZ À EFFET DE SERRE QUI RÉCHAUFFENT L’ATMOSPHÈRE? ", 3, "A Les transports ### B L’industrie ### C Le bâtiment", "B", 3
        ));

        questions.add(new Question(
                120, "QU’EST-CE QUE L’AGROÉCOLOGIE?", 3, "A Des techniques agricoles qui respectent l’environnement ### B Le fait que les consommateurs aillent cueillir directement leurs fruits et légumes ### C Une méthode moyenâgeuse pour faire pousser des plantes", "A", 3
        ));

        questions.add(new Question(
                121, "QUELLE QUANTITÉ DE DÉCHETS FINIT EN MER CHAQUE MINUTE DANS LE MONDE?", 3, "A L’équivalent de 60 caddies ### B L’équivalent de 180 caddies ### C L’équivalent de 1 600 caddies", "C", 3
        ));

        questions.add(new Question(
                122, "QUEL ÉCOGESTE PERMET D’AIDER À PROTÉGER LA PLANÈTE? ", 3, "A Manger plus pour gaspiller moins de nourritures ### B Manger les fruits et légumes « moches » ### C Boire moins d’eau pour en utiliser moins", "B", 3
        ));

        questions.add(new Question(
                123, "VRAI OU FAUX? L’EFFET DE SERRE EST DÛ À L’HOMME.", 2, "A Vrai- l’activité humaine émet des gaz à l’origine de l’effet de serres ### B Faux- sans effet de serre la vie n’aurait pu apparaître sur Terre et s’y développer", "B", 3
        ));

        questions.add(new Question(
                124, "VRAI OU FAUX? SANS GAZ À EFFET DE SERRE- LA TEMPÉRATURE SERAIT DE -19 °C.", 2, "A Vrai ### B Faux", "A", 3
        ));

        questions.add(new Question(
                125, "VRAI OU FAUX? LES TROUS DANS LA COUCHE D’OZONE AGGRAVENT LE CHANGEMENT CLIMATIQUE. ", 2, "A Vrai ### B Faux", "B", 3
        ));

        questions.add(new Question(
                126, "QUEL POURCENTAGE DES ÉMISSIONS DE GAZ À EFFET DE SERRE EST DÛ À L’AGRICULTURE?", 3, "A 13 % ### B 24 % ### C 32 %", "B", 3
        ));

        questions.add(new Question(
                127, "LORSQUE LA TEMPÉRATURE AUGMENTE D’UN DEGRÉ- LA PRODUCTION MONDIALE DE CÉRÉALES DIMINUE D’ENVIRON: ", 3, "A 2 % ### B 5 % ### C 9 %", "B", 3
        ));

        questions.add(new Question(
                128, "DEPUIS LE DÉBUT DU XXIe SIÈCLE- COMBIEN D’ANNÉES RENTRENT DANS LE CLASSEMENT DES ANNÉES LES PLUS CHAUDES JAMAIS ENREGISTRÉES?", 3, "A 5 années ### B 9 années ### C 16 années", "C", 3
        ));

        questions.add(new Question(
                129, "EN 2015- À LA COP21 À PARIS- LES CHEFS D’ÉTAT SE SONT ENGAGÉS À METTRE EN PLACE DES MESURES POUR LIMITER LE RÉCHAUFFEMENT CLIMATIQUE À… ", 3, "A + 0-5 degré Celsius d’ici 2100 ### B + 2 degrés Celsius d’ici 2100 ### C + 5 degrés Celsius d’ici 2100", "B", 3
        ));

        questions.add(new Question(
                130, "DEPUIS 1979- LA BANQUISE PERD:", 3, "A 10 000 km2 chaque décennie (soit 1 000 terrains de foot) ### B 100 000 km2 chaque décennie (soit 10 000 terrains de foot) ### C 1 million de km2 chaque décennie (soit 100 000 terrains de foot)", "C", 3
        ));

        questions.add(new Question(
                131, "DEPUIS 1990- LES ÉMISSIONS GLOBALES DE DIOXYDE DE CARBONE (CO2 ) ONT AUGMENTÉ DE PRÈS DE:", 3, "A 15 % ### B 25 % ### C 50 %", "C", 3
        ));

        questions.add(new Question(
                132, "QU’EST-CE QU’ON APPELLE « LE POUMON DE LA TERRE »- EN PLUS DES FORÊTS? ", 3, "A L’Himalaya ### B L’océan ### C Le volcan", "B", 3
        ));

        questions.add(new Question(
                133, "AU RYTHME ACTUEL- D’ICI 2050- LA MASSE DE PLASTIQUES DANS LES OCÉANS SERA SUPÉRIEURE À CELLE DES POISSONS. ", 2, "A Vrai ### B Faux", "A", 3
        ));

        questions.add(new Question(
                134, "DEPUIS 1950- LA POPULATION DES OISEAUX MARINS A BAISSÉ DE: ", 3, "A 60 % ### B 70 % ### C 80 %", "B", 3
        ));

        questions.add(new Question(
                135, "QUEL POURCENTAGE DES ESPÈCES MARINES AVONS-NOUS IDENTIFIÉ?", 3, "A Environ 5 % ### B Environ 50 % ### C Plus de 80 %", "A", 3
        ));

        questions.add(new Question(
                136, "LA DISPARITION DES ESPÈCES MARINES EST CAUSÉE PAR: ", 4, "A La surpêche ### B La pollution des océans ### C Le réchauffement climatique ### D Toutes ces réponses", "D", 3
        ));

        questions.add(new Question(
                137, "QUELLE EST LA PART DES STOCKS DE POISSONS COMMERCIAUX QUI SONT SOIT SUREXPLOITÉS SOIT PLEINEMENT EXPLOITÉS? ", 3, "A 2 % ### B 51 % ### C 80 %", "C", 4
        ));

        questions.add(new Question(
                138, "QU’EST-CE QUE LA PÊCHE DURABLE? ", 4, "A Une pêche qui respecte les stocks de poissons et leur renouvellement ### B Une pêche qui permet de rémunérer correctement les pêcheurs ### C Une pêche qui interdit certaines pratiques nocives ### D Toutes ces réponses", "D", 4
        ));

        questions.add(new Question(
                139, "COMBIEN DE PERSONNES DÉPENDENT DE LA BIODIVERSITÉ MARINE POUR VIVRE? ", 3, "A 500 millions de personnes ### B 1 milliard de personne ### C Plus de 3 milliards de personnes", "C", 4
        ));

        questions.add(new Question(
                140, "QUELLE PART DES GAZ À EFFET DE SERRE LES OCÉANS ABSORBENT-ILS? ", 3, "A 5 % ### B 15 % ### C 30 %", "C", 4
        ));

        questions.add(new Question(
                141, "POUR QUELLE ONG LA DESSINATRICE PÉNÉLOPE BAGIEU A-T-ELLE ILLUSTRÉ LA PÉTITION « PREND 5 MINUTES ET SIGNE- COPAIN » QUI DÉNONCE LES MÉFAITS DE LA SURPÊCHE? ", 3, "A Bloups ### B Léon Blum ### C Bloom", "C", 4
        ));

        questions.add(new Question(
                142, "COMBIEN D’HECTARES DE FORÊTS DISPARAISSENT CHAQUE ANNÉE? ", 3, "A 1 300 d’hectares (soit 1 530 terrains de foot) ### B 13 000 d’hectares (soit 15 300 terrains de foot) ### C 13 millions d’hectares (soit 15 millions de terrains de foot)", "C", 4
        ));

        questions.add(new Question(
                143, "POURQUOI COUPE-T-ON PRINCIPALEMENT LES ARBRES AUJOURD’HUI? ", 3, "A Pour agrandir les terres agricoles ### B Pour faire du papier ### C Pour se chauffer", "A", 4
        ));

        questions.add(new Question(
                144, "DANS LE MONDE- COMBIEN DE PERSONNES VIVENT GRÂCE AUX FORÊTS? ", 3, "A 1 personne sur 100 ### B 1 personne sur 20 ### C 1 personne sur 5", "C", 4
        ));

        questions.add(new Question(
                145, "QUELLE EST LA PART DES PLANTES DANS L’ALIMENTATION MONDIALE? ", 3, "A 5 % ### B 45 % ### C 80 %", "C", 4
        ));

        questions.add(new Question(146, "LES FORÊTS JOUENT UN RÔLE ESSENTIEL DANS LA LUTTE CONTRE LE CHANGEMENT CLIMATIQUE CAR: ", 3, "A Ce sont des lieux de promenade ### B Les arbres produisent de l’oxygène ### C On a besoin de bois pour la construction", "B", 4
        ));

        questions.add(new Question(
                147, "LA BIODIVERSITÉ C’EST: ", 3, "A L’ensemble de tous les êtres vivants sur Terre ### B Les produits bio disponibles en magasin ### C L’ensemble des plantes- des arbres- des fleurs... qui couvrent nos forêts", "A", 4
        ));

        questions.add(new Question(
                148, "QU’EST-CE QU’UNE TERRE ARABLE?", 3, "A C’est une terre qui peut être labourée ou cultivée ### B C’est une terre située sur le continent africain ### C C’est une terre de reproduction des lapins", "A", 4
        ));

        questions.add(new Question(
                149, "DE TOUTES LES ESPÈCES ANIMALES CONNUES… ", 3, "A 3 % sont déjà éteintes et 12 % en voie d’extinction ### B 5 % sont déjà éteintes et 16 % en voie d’extinction ### C 8 % sont déjà éteintes et 22 % en voie d’extinction", "C", 4
        ));

        questions.add(new Question(
                150, "COMBIEN DE PLANTES CULTIVÉES ONT DISPARU EN UN SIÈCLE?", 3, "A 25 % des plantes cultivées ### B 50 % des plantes cultivées ### C 75 % des plantes cultivées", "C", 4
        ));

        questions.add(new Question(
                151, "EN 1970- LA POPULATION D’ÉLÉPHANTS EN AFRIQUE ÉTAIT ESTIMÉE À 1-3 MILLION D’INDIVIDUS. COMBIEN SONT-ILS AUJOURD’HUI? ", 3, "A 1 million ### B 350 000 ### C 20 000", "B", 4
        ));

        questions.add(new Question(
                152, "UN CASQUE BLEU EST:", 3, "A Un soldat de la force de maintien de la paix de l’ONU ### B Un gendarme maritime ### C Un cycliste", "A", 4
        ));

        questions.add(new Question(
                153, "UNE DÉMOCRATIE- C’EST UN PAYS DIRIGÉ PAR:", 3, "A Un groupe de personnes éduquées et riches ### B Des représentants élus par les habitants ### C Un président à vie", "B", 4
        ));

        questions.add(new Question(
                154, "EN 2016- SUR 197 PAYS DANS LE MONDE- COMBIEN SONT CONSIDÉRÉS EN PAIX? ", 3, "A 10 ### B 30 ### C 50", "A", 4
        ));

        questions.add(new Question(
                155, "EN 2017- QUEL ÉTAIT LE BUDGET DE L’ONU POUR LE MAINTIEN DE LA PAIX DANS LE MONDE?", 3, "A 6-8 milliards de dollars ### B 100 milliards de dollars ### C 1 747 milliards de dollars", "A", 4
        ));

        questions.add(new Question(
                156, "QUEL EST LE POINT COMMUN ENTRE LES ÉTATS-UNIS- LA RUSSIE- LA CHINE- LE ROYAUMEUNI ET LA FRANCE ? ", 3, "A Ce sont les pays qui vendent le plus d’armes dans le monde ### B Ce sont les pays qui financent le plus la paix dans le monde ### C Ce sont les pays qui financent le plus la paix et la guerre dans le monde", "C", 4
        ));

        questions.add(new Question(
                157, "QUELLES SONT LES INSTITUTIONS LES PLUS TOUCHÉES PAR LA CORRUPTION DANS LE MONDE?", 3, "A L’éducation et la santé ### B La justice et la police ### C Les banques et l’audiovisuel", "B", 4
        ));

        questions.add(new Question(
                158, "COMBIEN D’ENFANTS VIVENT DANS DES RÉGIONS EN PROIE À UN CONFLIT? ", 3, "A 1 enfant sur 100- soit 22 millions d’enfants ### B 1 enfant sur 25- soit 88 millions d’enfants ### C 1 enfant sur 9- soit 230 millions d’enfants", "C", 4
        ));

        questions.add(new Question(
                159, "QU’EST-CE QUE LE COMMERCE ÉQUITABLE APPORTE AUX PRODUCTEURS? ", 3, "A Les mêmes prix fixes partout dans le monde ### B Des prix justes et un salaire décent ### C Le droit de vendre ses produits à l’étranger", "B", 5
        ));

        questions.add(new Question(
                160, "QU’EST-CE QU’UN PARADIS FISCAL?", 3, "A Un territoire dans lequel on paye peu ou pas d’impôts ### B Une petite île ### C Un jardin public", "A", 5
        ));

        questions.add(new Question(
                161, "COMBIEN D’ENFANTS DE 6 À 17 ANS VIVENT DANS UN PAYS DANS LEQUEL LES PUNITIONS CORPORELLES À L’ÉCOLE NE SONT PAS COMPLÈTEMENT PROSCRITES?", 3, "A 1 enfant sur 10 ### B 1 enfant sur 5 ### C 1 enfant sur 2", "C", 5
        ));

        questions.add(new Question(
                162, "QU’EST-CE QUE L’ONU? ", 3, "A L’Opéra National d’Ukraine- créé en 1867 ### B L’Organisation des Nations Unies ### C L’Organisme des Nouveaux Urbains", "B", 5
        ));

        questions.add(new Question(
                163, "QUI PEUT AGIR AFIN D’ATTEINDRE LES 17 ODD? ", 5, "A Les citoyens ### B Les gouvernements ### C Les entreprises ### D Les organisations non gouvernementales A164 ### E Toutes ces réponses", "E", 6
        ));

        questions.add(new Question(
                164, "PARMI LES PROPOSITIONS SUIVANTES- LESQUELLES POURRAIENT AIDER À FINANCER LE DÉVELOPPEMENT DURABLE ET LA LUTTE CONTRE LA PAUVRETÉ? ", 4, "A Lutter contre la corruption ### B Lutter contre l’évasion fiscale vers les paradis fiscaux ### C Lutter contre l’appropriation des ressources naturelles et des terres ### D Toutes ces réponses", "D", 6
        ));

        questions.add(new Question(
                165, "LA POPULATION MONDIALE S’ÉLÈVE ACTUELLEMENT À 7-5 MILLIARDS D’INDIVIDUS. À COMBIEN DEVRAIT-ELLE PASSER EN 2050?", 3, "A 6 milliards ### B 7-5 milliards ### C 9 milliards", "C", 6
        ));

        questions.add(new Question(
                166, "QU’EST-CE QUE L’AIDE AU DÉVELOPPEMENT? ", 3, "A Une aide financière des pays les plus favorisés à destination des pays moins favorisés ### B Un soutien aux développeurs informatiques ### C Une aide à l’orientation professionnelle", "A", 6
        ));

        questions.add(new Question(
                167, "LE SOMMET DE LA TERRE- À RIO EN 1992- DÉFINIT LES TROIS PILIERS DU DÉVELOPPEMENT DURABLE- QUI SONT…", 3, "A La puissance financière- la régulation du commerce mondial et le contrôle des médias ### B L’éducation- l’agriculture et la santé ### C Le progrès économique- le bien-être social et la protection de l’environnement", "C", 7
        ));

        questions.add(new Question(
                168, "QUELLE SOMME EST DÉTOURNÉE CHAQUE ANNÉE DES PAYS EN DÉVELOPPEMENT VERS LES PAYS DU NORD?", 3, "A 125 milliards d’euros ### B 500 milliards d’euros ### C 800 milliards d’euros", "C", 7
        ));

        questions.add(new Question(
                169, "DEPUIS 2009- LE NOMBRE D’INTERNAUTES DANS LES PAYS EN DÉVELOPPEMENT A :", 3, "A Diminué ### B Stagné ### C Plus que doublé", "C", 7
        ));

        questions.add(new Question(
                170, "DANS LE MONDE- PRÈS DE:", 3, "A 2 milliards de personnes utilisent Internet et un tiers d’entre elles vivent dans des pays en développement ### B 3 milliards de personnes utilisent Internet et la moitié d’entre elles vivent dans des pays en développement ### C 4 milliards de personnes utilisent Internet et deux tiers d’entre elles vivent dans des pays en développement", "C", 7
        ));

        questions.add(new Question(
                171, "VRAI OU FAUX? LES TECHNOLOGIES DE L’INFORMATION ET DE LA COMMUNICATION (TIC) SONT BÉNÉFIQUES POUR LE DÉVELOPPEMENT.", 2, "A Vrai ### B Faux", "A", 23
        ));


    }


}
