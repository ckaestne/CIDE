-- This module is imported by Pic (but _not_ reexported). It is not imported by Prelude

module Midi (MidiEvent(NoteOff, NoteOn, SustainOff, SustainOn), Track(Track), Sequence(Sequence), 
           mkMidiSequence, midiFile, midiInstrumentName, playMidi, stopMidi, writeMidiFile
) where {

  -- type MidiSequence is a primitive type; it represents a playable Midi sequence.

  -- Type synonmys for Midi entities and others
  type Instrument = Int;          -- Instrument 0 is a piano,  8 a celesta, 24 a guitar, etc.
  type Pitch      = Int;          -- A pitch of 60 corresponds to middle C 
  type Velocity   = Int;          -- Eg, 80.  (Max is 100 ?)
  type Tick       = Int;          -- Time from the start of the track. There are 16 ticks per quarter note

  type FileName   = String;       -- Midi files typically have a ".mid" suffix

 
  -- This datatype corresponds to (a subset of) Midi events
  data MidiEvent 
    = NoteOff {pitch :: Pitch, velocity :: Int, tick :: Int}
    | NoteOn  {pitch :: Pitch, velocity :: Int, tick :: Int}
    | SustainOff  {tick :: Int}
    | SustainOn {tick :: Int};

  -- This datatype corresponds to a Midi track
  data Track = Track {instrument :: Instrument, events :: [MidiEvent]};

  -- This datatype corresponds to a Midi sequence
  data Sequence = Sequence {tracks :: [Track]};


  -- MidiSequence is a primitive type. Values of this type are playable Midi sequences.

  -- Create an actual MidiSequence
  primitive primMkMidiSequence :: Sequence -> MidiSequence;
  mkMidiSequence = primMkMidiSequence;


  -- Read in a Midi sequence from a Midi file 
  primitive primMidiFile  :: FileName -> MidiSequence;
  midiFile = primMidiFile;


  -- Determine the name of a specified Midi instrument 
  primitive primMidiInstrumentName :: Int -> String;
  midiInstrumentName = primMidiInstrumentName;


  -- "Actions" are literals that can be incorporated in PicAction pictures and
  -- thence executed by double-clicking the picture element.

  -- Create a "play Midi sequence" action
  primitive primPlayMidi :: MidiSequence -> Action;
  playMidi = primPlayMidi;

  
  -- The "stop Midi sequence" action
  primitive primStopMidi :: Action;
  stopMidi = primStopMidi;


  -- Write a Midi sequence to file
  primitive primWriteMidiFile :: String -> MidSequence -> Action;
  writeMidiFile = primWriteMidiFile
}
