package me.chiefbeef.core.aesthetic.sound.elements;

public enum Note {
	
	_1(0.5f, 		"F#", 0), // F# / Gb
	_2(0.529732f, 	"G" , 0), // G
	_3(0.561231f,   "G#", 0), // G# / Ab
	_4(0.594604f,	"A" , 0), // A
	_5(0.629961f,	"A#", 0), // A# / Bb
	_6(0.667420f,	"B" , 0), // B
	_7(0.707107f,	"C" , 0), // C
	_8(0.749154f,	"C#", 0), // C# / Db
	_9(0.793701f,	"D" , 0), // D
	_10(0.840896f,	"D#", 0), // D# / Eb
	_11(0.890899f,	"E" , 0), // E
	_12(0.943874f,	"F" , 0), // F

	_13(1f,			"F#", 1), // F# / Gb
	_14(1.059463f,	"G" , 1), // G
	_15(1.122462f,	"G#", 1), // G# / Ab
	_16(1.189207f,	"A" , 1), // A
	_17(1.259921f,	"A#", 1), // A# / Bb
	_18(1.334840f,	"B" , 1), // B
	_19(1.414214f,	"C" , 1), // C	
	_20(1.498307f,	"C#", 1), // C# / Db
	_21(1.587401f,	"D" , 1), // D
	_22(1.681793f,	"D#", 1), // D# / Eb
	_23(1.887749f,	"E" , 1), // E
	_24(2f,			"F" , 1); // F
	
	private float pitch;
	private String note;
	private int octave;
	
	private Note(float pitch, String note, int octave) {
		this.pitch = pitch;
		this.note = note;
		this.octave = octave;
	}

	public float getPitch() {
		return pitch;
	}
	
	public String getNote() {
		return note;
	}
	
	public int getOctave() {
		return octave;
	}
}
