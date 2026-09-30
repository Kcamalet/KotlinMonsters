package monstre

import dresseur.Entraineur
import kotlin.math.pow

/**
 * Représente un individu monstre, c'est-à-dire un monstre avec lequel le joueur interagit
 * (monstre sauvage, monstre de l'équipe du joueur, monstre d'un autre dresseur).
 * Deux individus peuvent appartenir à la même espèce, par exemple deux Canaros.
 *
 * @property id Identifiant unique de l'individu.
 * @property nom Nom de l'individu.
 * @property espece Espèce à laquelle appartient l'individu.
 * @property entraineur Entraîneur du monstre, ou null si c'est un monstre sauvage.
 * @param expInit Expérience initiale du monstre.
 */
class IndividuMonstre(
    val id: Int,
    var nom: String,
    expInit: Double,
    val espece: EspeceMonstre,
    var entraineur: Entraineur? = null,
) {
    var niveau: Int = 1
    var attaque: Int = espece.baseAttaque + (-2..2).random()
    var defense: Int = espece.baseDefense + (-2..2).random()
    var vitesse: Int = espece.baseVitesse + (-2..2).random()
    var attaqueSpe: Int = espece.baseAttaqueSpe + (-2..2).random()
    var defenseSpe: Int = espece.baseDefenseSpe + (-2..2).random()
    var pvMax: Int = espece.basePv + (-5..5).random()
    var potentiel: Double = (50..200).random() / 100.0

    /**
     * @property exp Expérience actuelle du monstre.
     * Quand l'expérience atteint un palier, le monstre gagne un niveau.
     */
    var exp: Double = 0.0
        get() = field
        set(value) {
            field = value
            val estNiveau1 = this.niveau == 1
            while (field >= palierExp(this.niveau + 1)) {
                levelUp()
                if (!estNiveau1) {
                    println("Le monstre $nom est maintenant niveau $niveau !")
                }
            }
        }

    /**
     * @property pv Points de vie actuels.
     * Ne peut pas être inférieur à 0 ni supérieur à [pvMax].
     */
    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            field = if (nouveauPv < 0) {
                0
            } else if (nouveauPv > pvMax) {
                pvMax
            } else {
                nouveauPv
            }
        }

    init {
        this.exp = expInit // applique le setter et déclenche un éventuel level-up
    }

    /**
     * Calcule l'expérience totale nécessaire pour atteindre un niveau donné.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */
    fun palierExp(niveau: Int): Double {
        return 100 * (niveau - 1).toDouble().pow(2.0)
    }

    /**
     * Fait monter le monstre d'un niveau.
     * Augmente le niveau ainsi que toutes les caractéristiques du monstre.
     * Les points de vie actuels sont augmentés du nombre de pvMax gagnés.
     */
    fun levelUp() {
        this.niveau += 1

        val gainAttaque = Math.round(espece.modAttaque * potentiel).toInt() + (-2..2).random()
        val gainDefense = Math.round(espece.modDefense * potentiel).toInt() + (-2..2).random()
        val gainVitesse = Math.round(espece.modVitesse * potentiel).toInt() + (-2..2).random()
        val gainAttaqueSpe = Math.round(espece.modAttaqueSpe * potentiel).toInt() + (-2..2).random()
        val gainDefenseSpe = Math.round(espece.modDefenseSpe * potentiel).toInt() + (-2..2).random()
        val gainPvMax = Math.round(espece.modPv * potentiel).toInt() + (-5..5).random()

        this.attaque += gainAttaque
        this.defense += gainDefense
        this.vitesse += gainVitesse
        this.attaqueSpe += gainAttaqueSpe
        this.defenseSpe += gainDefenseSpe
        this.pvMax += gainPvMax
        this.pv += gainPvMax
    }
    /**
     * Attaque un autre [IndividuMonstre] et inflige des dégâts.
     *
     * Les dégâts sont calculés de manière très simple pour le moment :
     * `dégâts = attaque - (défense / 2)` (minimum 1 dégât).
     *
     * @param cible Monstre cible de l'attaque.
     */
    fun attaquer(cible: IndividuMonstre) {
        val degatBrut = this.attaque
        var degatTotal = degatBrut - (this.defense / 2)

        if (degatTotal < 1) {
            degatTotal = 1
        }

        val pvAvant = cible.pv
        cible.pv -= degatTotal
        val pvApres = cible.pv

        println("${this.nom} inflige ${pvAvant - pvApres} dégâts à ${cible.nom}")
    }
    /**
     * Demande au joueur de renommer le monstre.
     * Si l'utilisateur entre un texte vide, le nom n'est pas modifié.
     */
    fun renommer() {
        println("Renommer ${this.nom} ?")
        val nouveauNom = readln()

        if (nouveauNom.isNotBlank()) {
            this.nom = nouveauNom
        }
    }
    /**
     * Affiche les caractéristiques du monstre à côté de son art ASCII.
     */
    fun afficheDetail() {
        val art = espece.afficheArt()
        val artLines = art.lines()

        val details = listOf(
            "=====================",
            "Nom: ${this.nom}   Niveau: ${this.niveau}",
            "Exp: ${this.exp}",
            "PV: ${this.pv} / ${this.pvMax}",
            "=====================",
            "Atq : ${this.attaque}  Def : ${this.defense}  Vitesse : ${this.vitesse}",
            "AtqSpe : ${this.attaqueSpe}  DefSpe : ${this.defenseSpe}",
            "====================="
        )

        val maxArtWidth = artLines.maxOf { it.length }
        val maxLines = maxOf(artLines.size, details.size)

        for (i in 0 until maxLines) {
            val artLine = if (i < artLines.size) artLines[i] else ""
            val detailLine = if (i < details.size) details[i] else ""
            val paddedArt = artLine.padEnd(maxArtWidth + 4)
            println(paddedArt + detailLine)
        }
    }
}