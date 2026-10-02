package jeu

import joueur
import monstre.IndividuMonstre

/**
 * Représente un combat entre un monstre du joueur et un monstre sauvage.
 *
 * @property monstreJoueur Le monstre actuellement envoyé par le joueur.
 * @property monstreSauvage Le monstre sauvage affronté.
 */
class CombatMonstre(
    var monstreJoueur: IndividuMonstre,
    var monstreSauvage: IndividuMonstre,
) {
    var round: Int = 1

    /**
     * Vérifie si le joueur a perdu le combat.
     *
     * Condition de défaite :
     * - Aucun monstre de l'équipe du joueur n'a de PV > 0.
     *
     * @return `true` si le joueur a perdu, sinon `false`.
     */
    fun gameOver(): Boolean {
        for (monstre in joueur.equipeMonstre) {
            if (monstre.pv > 0) {
                return false
            }
        }
        return true
    }

    /**
     * Indique si le joueur a gagné le combat.
     *
     * Il y a deux façons de gagner : capturer le monstre sauvage,
     * ou amener ses PV à 0 (dans ce cas seulement, le monstre du joueur gagne de l'expérience).
     *
     * @return `true` si le joueur a gagné, sinon `false`.
     */
    fun joueurGagne(): Boolean {
        if (monstreSauvage.pv <= 0) {
            println("${joueur.nom} a gagné !")
            val gainExp = monstreSauvage.exp * 0.20
            monstreJoueur.exp += gainExp
            println("${monstreJoueur.nom} gagne $gainExp exp")
            return true
        } else if (monstreSauvage.entraineur == joueur) {
            println("${monstreSauvage.nom} a été capturé !")
            return true
        } else {
            return false
        }
    }

    /**
     * Fait attaquer le monstre sauvage s'il est encore en vie.
     */
    fun actionAdversaire() {
        if (monstreSauvage.pv > 0) {
            monstreSauvage.attaquer(monstreJoueur)
        }
    }
    /**
     * Permet au joueur de choisir et d'effectuer une action pendant son tour.
     *
     * @return `true` si le combat doit continuer, `false` sinon.
     */
    fun actionJoueur(): Boolean {
        if (gameOver()) {
            return false
        }

        println("Que voulez-vous faire ?")
        println("1 - Attaquer")
        println("2 - Utiliser un objet")
        println("3 - Changer de monstre")
        val choixAction = readln().toIntOrNull()

        if (choixAction == 1) {
            monstreJoueur.attaquer(monstreSauvage)
        } else if (choixAction == 2) {
            for (i in joueur.sacAItems.indices) {
                println("$i - ${joueur.sacAItems[i].nom}")
            }
            val indexChoix = readln().toIntOrNull()

            if (indexChoix != null && indexChoix in joueur.sacAItems.indices) {
                val objetChoisi = joueur.sacAItems[indexChoix]

                if (objetChoisi is Utilisable) {
                    val captureReussie = objetChoisi.utiliser(monstreSauvage)
                    if (captureReussie) {
                        return false
                    }
                } else {
                    println("Objet non utilisable")
                }
            } else {
                println("Choix invalide")
            }
        } else if (choixAction == 3) {
            for (i in joueur.equipeMonstre.indices) {
                val monstre = joueur.equipeMonstre[i]
                if (monstre.pv > 0) {
                    println("$i - ${monstre.nom} (${monstre.pv}/${monstre.pvMax} PV)")
                }
            }
            val indexChoix = readln().toIntOrNull()

            if (indexChoix != null && indexChoix in joueur.equipeMonstre.indices) {
                val choixMonstre = joueur.equipeMonstre[indexChoix]

                if (choixMonstre.pv <= 0) {
                    println("Impossible ! Ce monstre est KO")
                } else {
                    println("${choixMonstre.nom} remplace ${monstreJoueur.nom}")
                    monstreJoueur = choixMonstre
                }
            } else {
                println("Choix invalide")
            }
        } else {
            println("Choix invalide")
        }

        return true
    }
}