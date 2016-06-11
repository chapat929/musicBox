package com.chandni;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class MusicTest1 {
    public static void main(String[] args) {
        final MusicTest1 mt = new MusicTest1();
        mt.play();
    }

    private void play() {
        try {
            final Sequencer sequencer = MidiSystem.getSequencer();
            System.out.println("Successfully got a sequencer");
        } catch (MidiUnavailableException e) {
            System.out.println("Bummer");
        }
    }

}
