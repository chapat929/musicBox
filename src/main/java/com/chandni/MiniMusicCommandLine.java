package com.chandni;

import javax.sound.midi.*;

public class MiniMusicCommandLine {
    public static void main(String[] args) {
        final MiniMusicCommandLine miniMusicCommandLine = new MiniMusicCommandLine();
        if (args.length < 2) {
            System.out.println("Don't forget to enter the instrument and note args");
        } else {
            final int instrument = Integer.parseInt(args[0]);
            final int note = Integer.parseInt(args[1]);
            miniMusicCommandLine.play(instrument, note);
        }
    }

    private void play(int instrument, int note) {
        try {
            final Sequencer player = MidiSystem.getSequencer();
            player.open();
            final Sequence sequence = new Sequence(Sequence.PPQ, 4);
            final Track track = sequence.createTrack();

            MidiEvent event = null;

            final ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrument, 0);
            final MidiEvent changeInstrument = new MidiEvent(first, 1);
            track.add(changeInstrument);

            final ShortMessage noteA = new ShortMessage();
            noteA.setMessage(144, 1, note, 100);
            final MidiEvent noteOn = new MidiEvent(noteA, 1);
            track.add(noteOn);

            final ShortMessage noteB = new ShortMessage();
            noteB.setMessage(128, 1, note, 100);
            final MidiEvent noteOff = new MidiEvent(noteB, 16);
            track.add(noteOff);
            player.setSequence(sequence);
            player.start();

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
}
