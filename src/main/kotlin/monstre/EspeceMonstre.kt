package monstre

import java.io.File

/**
 * Représente une espèce de monstre (et non un individu).
 * Une espèce définit les caractéristiques communes à tous les monstres de cette espèce.
 *
 * @property id Identifiant unique de l'espèce.
 * @property nom Nom de l'espèce (ex : Aquamy).
 * @property type Type de l'espèce (ex : Meteo, Animal).
 * @property baseAttaque Statistique de base d'attaque.
 * @property baseDefense Statistique de base de défense.
 * @property baseVitesse Statistique de base de vitesse.
 * @property baseAttaqueSpe Statistique de base d'attaque spéciale.
 * @property baseDefenseSpe Statistique de base de défense spéciale.
 * @property basePv Statistique de base des points de vie.
 * @property modAttaque Multiplicateur d'attaque utilisé lors de la montée de niveau.
 * @property modDefense Multiplicateur de défense utilisé lors de la montée de niveau.
 * @property modVitesse Multiplicateur de vitesse utilisé lors de la montée de niveau.
 * @property modAttaqueSpe Multiplicateur d'attaque spéciale utilisé lors de la montée de niveau.
 * @property modDefenseSpe Multiplicateur de défense spéciale utilisé lors de la montée de niveau.
 * @property modPv Multiplicateur de points de vie utilisé lors de la montée de niveau.
 * @property description Description de l'espèce (255 caractères max).
 * @property particularites Particularités de l'espèce.
 * @property caractères Traits de caractère de l'espèce.
 */
class EspeceMonstre(
    var id: Int,
    var nom: String,
    var type: String,
    val baseAttaque: Int,
    val baseDefense: Int,
    val baseVitesse: Int,
    val baseAttaqueSpe: Int,
    val baseDefenseSpe: Int,
    val basePv: Int,
    val modAttaque: Double,
    val modDefense: Double,
    val modVitesse: Double,
    val modAttaqueSpe: Double,
    val modDefenseSpe: Double,
    val modPv: Double,
    val description: String = "",
    val particularites: String = "",
    val caractères: String = "",
) {
    /**
     * Affiche la représentation artistique ASCII du monstre.
     *
     * @param deFace Détermine si l'art affiché est de face (true) ou de dos (false).
     * La valeur par défaut est true.
     * @return Une chaîne de caractères contenant l'art ASCII du monstre avec les codes couleur ANSI.
     * L'art est lu à partir d'un fichier texte dans le dossier resources/art.
     */
    fun afficheArt(deFace: Boolean = true): String {
        val nomFichier = if (deFace) "front" else "back"
        val art = File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "∕")
        return safeArt.replace("\\u001B", "\u001B")
    }
}