package main.tournoi;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * La classe décrit un chronomètre
 * @classe Chronometre
 * @extends JPanel
 */
public class Chrono extends JPanel {

    /**
     * serialVersionUID : numero de serie
     */
    private static final long serialVersionUID = 1L;

    /**
     * f : Font appliqué au texte
     */
    private static Font f = new Font("Book Antiqua", Font.BOLD, 25);

    /**
     * timer : timer servant à décrémenter le chronometre
     */
    private Timer timer;

    /**
     * couleur : couleur de fond du chronometre
     */
    private Color couleur = Color.orange;

    /**
     * tempsRestant : temps restant
     */
    private int tempsRestant;

    /**
     * temps : temps initial
     */
    private double temps;

    /**
     * actif : si le chrono est en cours
     */
    private boolean actif = false;

    /**
     * previous : variable aidant à la création du dégradé de couleur
     */
    private double previous = 0;

    /**
     * Construction du chronometre
     * @param N : le nombre de secondes initial
     */
    public Chrono(int N) {
        timer = createTimer();
        timer.start();
        setOpaque(false);
        setPreferredSize(new Dimension(144, 144));
        this.setTempsRestant(N);
        this.setTemps(N);
    }

    /**
     * Permet de démarrer le chronometre
     */
    public void start() {
        timer.start();
        actif = true;
    }

    /**
     * Permet d'arreter le chronometre
     */
    public void stop() {
        timer.stop();
        actif = false;
    }

    /**
     * Methode qui crée un Timer
     * @return le timer crée
     */
    private Timer createTimer() {
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (tempsRestant > 0) {
                    if(tempsRestant >= temps/2) {       //augmenter r
                        if(tempsRestant == temps) {
                            couleur = new Color(0, 255, 0);
                        } else {
                            previous += 255 / (temps / 2);
                            if (previous >= 1) {
                                try {
                                    couleur = new Color(couleur.getRed() + (int)previous, couleur.getGreen(), couleur.getBlue());
                                } catch(Exception e) {
                                    e.printStackTrace();
                                }
                                previous -= (int)previous;
                            }
                        }
                    } else {  //baisser g
                        previous += 255/(temps/2);
                        if (previous >= 1) {
                            try {
                                couleur = new Color(couleur.getRed(), couleur.getGreen()-(int)previous, couleur.getBlue());
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                            previous -= (int)previous;
                        }
                    }
                    tempsRestant--;
                    repaint();
                } else {
                    timer.stop();
                    //signal sonore
                    String soundName = "src/main/resources/son.wav";
                    AudioInputStream audioInputStream;
                    try {
                        audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();
                        clip.start();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erreur:"+e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    //fin signal sonore
                }
            }
        };
        return new Timer(1000, action);
    }

    /**
     * Redéfinition de la méthode paintCompnent
     * @param g
     */
    public void paintComponent(Graphics g) {
        this.drawCircle(g, 70, 70, 60);
    }

    /**
     * Fonction qui permet de dessiner le chronometre
     * @param cg      : element graphique
     * @param xCenter : abscisse du centre du cercle
     * @param yCenter : ordonnee du centre du cercle
     * @param r       : rayon du cercle
     */
    public void drawCircle(Graphics cg, int xCenter, int yCenter, int r) {
        cg.setColor(Color.white);
        cg.fillOval(xCenter - r, yCenter - r, 2 * r, 2 * r);
        cg.setColor(couleur);
        cg.fillArc(xCenter - r, yCenter - r, 2 * r, 2 * r, 90, -(360 - tempsRestant * 360 / (int)temps));
        cg.setColor(Color.black);
        cg.setFont(f);

        int minutes = this.tempsRestant / 60;
        int secondes = this.tempsRestant - (60 * minutes);
        if (secondes > 9) {
            cg.drawString(minutes + ":" + secondes, 40, 80);
        } else {
            cg.drawString(minutes + ":" + "0" + secondes, 40, 80);
        }
    }

    /**
     * Méthode permetant de modifier la valeur du temps restant
     * @param tempsRestant : la nouvelle valeur souhaité pour la variable
     */
    public void setTempsRestant(int tempsRestant) {
        this.tempsRestant = tempsRestant;
    }

    /**
     * Méthode qui retourne la durée actuelle du chrono
     * @return la valeur en question
     */
    public double getTemps() {
        return temps;
    }

    /**
     * Méthode qui retourne la durée actuelle du chrono en minutes
     * @return la valeur en question
     */
    public int getTempsMin() {
        return (int)(temps/60);
    }

    /**
     * Méthode qui retourne la durée actuelle du chrono en secondes
     * @return la valeur en question
     */
    public int getTempsSec() {
        return (int)(temps%60);
    }

    /**
     * Méthode permetant de modifier la valeur de la durée du chrono
     * @param temps : la nouvelle valeur souhaitée pour la variable
     */
    public void setTemps(int temps) {
        this.temps = temps;
    }

    /**
     * Méthode qui retourne si le chronometre est actif ou en pause
     * @return true si il est actif, false si il est en pause
     */
    public boolean getActif() {
        return actif;
    }

}