package com.chandni;

import javax.sound.midi.*;

public class MiniMiniMusicApp {
    public static void main(String[] args) {
        MiniMiniMusicApp miniMiniMusicApp = new MiniMiniMusicApp();
        miniMiniMusicApp.play();

    }

    private void play() {
        try {
            final int note = 40;
            final int duration = 20;

            final Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            final Sequence sequence = new Sequence(Sequence.PPQ, 4);
            final Track track = sequence.createTrack();

            final ShortMessage changeInstrument = new ShortMessage();
            changeInstrument.setMessage(192, 1, 127, 100);

            final MidiEvent newInstrument = new MidiEvent(changeInstrument, 1);
            track.add(newInstrument);

            final ShortMessage noteA = new ShortMessage();
            noteA.setMessage(144, 1, note, 100);
            final MidiEvent noteOn = new MidiEvent(noteA, 1);
            track.add(noteOn);

            final ShortMessage noteB = new ShortMessage();
            noteB.setMessage(128, 1, note, 100);
            final MidiEvent noteOff = new MidiEvent(noteB, duration);
            track.add(noteOff);
            sequencer.setSequence(sequence);
            sequencer.start();

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }


    }
}
