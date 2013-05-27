package com.example.web_advanced;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.vaadin.Application;
import com.vaadin.data.util.sqlcontainer.ColumnProperty;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.RowItem;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.TemporaryRowId;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.ui.*;

/**
 * Main application class.
 */
public class Web_advancedApplication extends Application {

	private TableQuery tq;
	private Window mainWindow;
	private Table table;
	private TextField titre_forum;
	private TextField type_forum;
	private SQLContainer forum;

	@Override
	public void init() {

		// n'oublie pas mettre tes coordonnÈes perso ici avec la bonne bdd et
		// les bon login/mdp de phpmyadmin
		mysql oracle = new mysql("jdbc:mysql://localhost:3306/web_advanced",
				"root", "root");
		forum = oracle.queryTable("test");
		tq = oracle.getTableQuery(); // une ligne que j'ai ajoutÈ pour rÈcupÈrer
										// la table query qu'on a crÈe dans
										// mysql.java

		// la table permet de faire une connection direct avec la table en bdd
		// mais aussi pour rÈcupÈrÈ des infos pour l'insertion.

		// new Table ("Nom de la table de vaadin", nom_de_la_table_de_la_bdd)
		table = new Table("FORUM", forum); // ma table s'appelle "forum" mais
											// vous mettez ce que vous voulez

		// quelques propriÈtÈs pour la table, mais Áa, on s'en fiche un peu,
		// c'est un c/c du prof
		table.setPageLength(10); // the number of rows per page
		table.setSizeFull(); // the table will fill the window
		table.setImmediate(true); // the server is notify each time I select a
									// row or modify values
		table.setSelectable(true); // the user is allowed to select rows
		table.setMultiSelect(false); // the user is not allowed to select more
										// than one row
		table.setEditable(true); // the user is allowed to modify values in the
									// selected row

		// on crÈe la fenÍtre qui s'affichera avec comme titre "Forum"
		mainWindow = new Window("Forum");
		// on l'affiche
		setMainWindow(mainWindow);

		/*
		 * le reste sert ‡ afficher des ÈlÈments dans la fenetre. : titre avec
		 * le numÈro de la question un label avec un input pour le titre un
		 * label avec un input pour le type ( un seul chiffre entre 0 et 9) un
		 * bouton enregistrer pour valider la question PS : oui il manque des
		 * trucs, mais c'est la version simplifiÈe.
		 */
		// 1Ëre colonne du tableau ( il n'y en a qu'une)
		VerticalLayout vl = new VerticalLayout();
		vl.setSpacing(true);
		vl.setMargin(true);
		mainWindow.setContent(vl); // insÈrer dans la fenetre
		// 1Ëre ligne de la fenetre le numÈro de la question
		HorizontalLayout hlIntro = new HorizontalLayout();
		hlIntro.setSpacing(true);
		hlIntro.setMargin(true);
		int numero = forum.size() + 1; // size() permet de compter le nombre de
										// ligne dans la table bdd.
		Label introduction = new Label("Question n∞ " + numero);
		hlIntro.addComponent(introduction); // le label est ajouter ‡ la
											// premiËre colonne

		// premiËre ligne du forum avec le titre
		HorizontalLayout hlFirst = new HorizontalLayout();
		hlFirst.setSpacing(true);
		hlFirst.setMargin(true);
		Label titreForum = new Label("Titre");
		hlFirst.addComponent(titreForum);
		titre_forum = new TextField();
		titre_forum.setImmediate(true);
		hlFirst.addComponent(titre_forum);

		// seconde ligne du forum avec le type
		HorizontalLayout hlSecond = new HorizontalLayout();
		hlSecond.setSpacing(true);
		hlSecond.setMargin(true);
		Label typeForum = new Label("Type");
		hlSecond.addComponent(typeForum);
		type_forum = new TextField();
		type_forum.setImmediate(true);
		type_forum.setMaxLength(1); // on limite le nombre de caractËre ‡ 1
		hlSecond.addComponent(type_forum);

		// derniËre ligne avec le bouton d'enregistrerement
		HorizontalLayout hl = new HorizontalLayout();
		hl.setSpacing(true);
		hl.setMargin(true);
		Button validerButton = new Button("Enregistrer", this, "validate");
		hl.addComponent(validerButton);

		// on assemble les lignes dans la colonne et la table ‡ la fin pour
		// afficher ce qu'il y a dans la bdd
		vl.addComponent(hlIntro);
		vl.addComponent(hlFirst);
		vl.addComponent(hlSecond);
		vl.addComponent(hl);
		vl.addComponent(table);
	}

	// fonction d'enregistrement, c'est l‡ que Áa se corse
	public void validate() throws UnsupportedOperationException, SQLException // "UnsupportedOperationException"
																				// est
																				// obligatoire
																				// sinon
																				// il
																				// souligne
																				// en
																				// rouge
	{

		try {

			tq.beginTransaction(); // dÈbut de la transaction

			Object emptyKey[] = new Object[tq.getPrimaryKeyColumns().size()]; // cet
																				// object
																				// j'ignore
																				// un
																				// peu
																				// ‡
																				// quoi
																				// il
																				// sert,
																				// mais
																				// il
																				// le
																				// faut
																				// pour
																				// l'inialisation

			RowId itemId = new TemporaryRowId(emptyKey); // RowId permet
															// d'identifier la
															// nouvelle ligne
															// qu'on va ajouter

			List<ColumnProperty> itemProperties = new ArrayList<ColumnProperty>(); // ColumnProperty
																					// qualifie
																					// la
																					// colonne
																					// de
																					// la
																					// table
																					// pour
																					// Ítre
																					// sur
																					// que
																					// ce
																					// qu'on
																					// va
																					// ajouter
																					// correspond
																					// ‡
																					// ce
																					// qu'il
																					// y
																					// a
																					// dans
																					// la
																					// bdd

			/*
			 * Dans ma table, il y a 3 colonnes id_forum qui est auto_increment
			 * et clÈ primaire ( car il faut une clÈ primaire sinon Áa ne marche
			 * pas) mais je m'en fiche un peu titre_forum l‡ o˘ le titre est
			 * ajoutÈ. Je crÈe une colomnProperty pour cette colonne avec en
			 * valeur ce qu'il y a dans l'input qui correspond la syntaxe est :
			 * ColumnProperty ("Nom_de_la_colonne_en_bdd", [si cette colonne est
			 * en lecture seul, je met false perso], [si on peu la modifier, je
			 * met true], [s'il peut Ítre null, moi j'ai mis false car il ne
			 * peut pas Ítre null], [si c'est une clÈ primaire, ici non car
			 * c'est id_forum, donc false],
			 * "la valeur que je veux enregistrer"[ici j'ai mis ""+ avant pour
			 * faire comprendre que c'est un type String que je veux
			 * enregistrer] le type de la colonne donc un String, mais vu que je
			 * ne sais pas l'Ècrire comme il faut, j'utilise une fonction qui me
			 * permet d'afficher le type de la colonne donc
			 * table.getType("le_nom_de_la_colonne")
			 */

			ColumnProperty cp = new ColumnProperty("titre_forum", false, true,
					false, false, "" + titre_forum.getValue(),
					table.getType("titre_forum"));

			// une fois que les propriÈtÈ de la colonne sont faite, on l'insËre
			// ‡ la liste
			itemProperties.add(cp);
			// je refais la mÍme chose pour la seconde colonne. Malheureusement,
			// y'a peut Ítre moyen de faire des boucle, mais je ne maitrise pas
			// les arrayList et les List
			cp = new ColumnProperty("type_forum", false, true, false, false, ""
					+ type_forum.getValue(), table.getType("type_forum"));
			itemProperties.add(cp);

			// je rassemble toute les donner dans une nouvelle ligne d'o˘
			// RowItem qui est ajouter ‡ la table en bdd
			tq.storeRow(new RowItem(forum, itemId, itemProperties));

			// puis on committe pour la sauvegarde
			tq.commit();

			// petit message si tout va bien
			mainWindow.showNotification("SUCCESS", "Transaction successfull",
					Window.Notification.TYPE_HUMANIZED_MESSAGE);
		} catch (Exception e) {
			// s'il y a un pb, un joli panneau rouge s'affiche"
			mainWindow.showNotification("ERROR", e.getMessage(),
					Window.Notification.TYPE_ERROR_MESSAGE);
		}

	}

}

/*
 * 
 * ENJOY !!
 * 
 * Made by Vicky le 24/05/2013 pour toute question je suis ‡ votre disposition
 * ;)
 */
