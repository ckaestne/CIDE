// original javascript control by phpBB Group (www.phpBB.com)
// complete php scripts + modified javascript control by Michael Weiss

// Startup variables
var imageTag = new Array();
var theSelection = new Array();

// Check for Browser & Platform for PC & IE specific bits
// More details from: http://www.mozilla.org/docs/web-developer/sniffer/browser_type.html
var clientPC = navigator.userAgent.toLowerCase(); // Get client info
var clientVer = parseInt(navigator.appVersion); // Get browser version
var is_ie = ((clientPC.indexOf("msie") != -1) && (clientPC.indexOf("opera") == -1));
var is_nav  = ((clientPC.indexOf('mozilla')!=-1) && (clientPC.indexOf('spoofer')==-1) && (clientPC.indexOf('compatible') == -1) && (clientPC.indexOf('opera')==-1) && (clientPC.indexOf('webtv')==-1) && (clientPC.indexOf('hotjava')==-1));
var is_win   = ((clientPC.indexOf("win")!=-1) || (clientPC.indexOf("16bit") != -1));
var is_mac    = (clientPC.indexOf("mac")!=-1);

// Define the forumCode tags
forumCode = new Array();
forumTags = new Array('[b]','[/b]','[i]','[/i]','[u]','[/u]','\n[quote]\n','\n[/quote]\n','\n[code]\n','\n[/code]\n','[list]\n[*]','[/list]','[list=]\n[*]','[/list]','[img]','[/img]','[url]','[/url]');

// formular
var formular;
var formular_name;

// set the formular
function setFormular(name) {
	formular = eval('document.'+name);
	formular_name = 'document.'+name;
}

// textareas
var textareas = new Array();

// current textarea
var textarea;
var current_nmb = -1;


function editSelection(insertBefore,insertAfter){
	var inputfield = textarea;
  inputfield.focus();
  /* für Internet Explorer */
	if(typeof document.selection != 'undefined') {
    var range = document.selection.createRange();
    var insText = range.text;
    range.text = insertBefore + insText + insertAfter;
    /* Anpassen der Cursorposition */
    range = document.selection.createRange();
    if (insText.length == 0) {
      range.move('character', -insertAfter.length);
    } else {
      range.moveStart('character', insertBefore.length + insText.length + insertAfter.length);      
    }
    range.select();
  }
  /* für neuere auf Gecko basierende Browser */
  else if(typeof inputfield.selectionStart != 'undefined')
  {
    /* Einfügen des Formatierungscodes */
    var start = inputfield.selectionStart;
    var end = inputfield.selectionEnd;
    var insText = inputfield.value.substring(start, end);
    inputfield.value = inputfield.value.substr(0, start) + insertBefore + insText + insertAfter + inputfield.value.substr(end);
    /* Anpassen der Cursorposition */
    var pos;
    if (insText.length == 0) {
      pos = start + insertBefore.length;
    } else {
      pos = start + insertBefore.length + insText.length + insertAfter.length;
    }
    inputfield.selectionStart = pos;
    inputfield.selectionEnd = pos;
  }
  /* für die übrigen Browser am ende anfügen */
  else
  {
    inputfield.value = inputfield.value + insertBefore + insertAfter;
  }	
}
function insertAtCursorPosition(atext){
	/* fügt einen text an der selektierten stelle ein */
	var inputfield = textarea;
  inputfield.focus();
	if (inputfield.createTextRange && inputfield.caretPos) {
		var caretPos = inputfield.caretPos;
		caretPos.text += atext;
	} else if(typeof inputfield.selectionStart != 'undefined' && inputfield.selectionStart) {
    var start = inputfield.selectionStart;
    inputfield.value = inputfield.value.substr(0, start) + atext + inputfield.value.substr(start);
    inputfield.selectionStart = start+atext.length;
    inputfield.selectionEnd = inputfield.selectionStart;
	} else {
		/* wenn nichts selektiert wurde, oder selektion nicht gelesen werden kann (z.B. Opera) am Ende einfügen */
    inputfield.value = inputfield.value + atext;
  }
	inputfield.focus();
}
function getSelectedText(){
	var inputfield = textarea;
  inputfield.focus();
	if(typeof document.selection != 'undefined') {
    var range = document.selection.createRange();
    return  range.text;
  } else if(typeof inputfield.selectionStart != 'undefined'){
  	/* für neuere auf Gecko basierende Browser */
    var start = inputfield.selectionStart;
    var end = inputfield.selectionEnd;
    return inputfield.value.substring(start, end);
  } else {
  	/* für die übrigen Browser */
    return '';
  }	
}


// registers different textareas
function registerTextArea(nmb,area) {
	textareas[nmb] = eval(formular_name + '.' + area);
	forumCode[nmb] = new Array();
	imageTag[nmb] = false;
	theSelection[nmb] = false;
	textareas[nmb].className = "text_deselect";
}

// change current textarea
function changeTextArea(nmb,classN) {
	if (current_nmb == nmb) return;
	if (current_nmb > -1) {
		textareas[current_nmb].className = "text_deselect";
	}
	textarea = textareas[nmb];
	current_nmb = nmb;
	textarea.className = classN;
	textarea.focus();
}

// change current textarea
function changeCSS(nmb,classN) {
	if (nmb == -1) return;
	if (current_nmb == nmb) return;
	if (current_nmb > -1) {
		textareas[current_nmb].className = "text_deselect";
	}
	textarea = textareas[nmb];
	current_nmb = nmb;
	textarea.className = classN;
}

// check user input length
function checkUserInputLength(area, maxLen, areaLenField) {
    var chars = eval(formular_name + '.' + areaLenField);
    var thisArea = eval(formular_name + '.' + area);
    var len = thisArea.value.length;
    chars.value = len_mess + (maxLen - len);
    if (len > maxLen) chars.className = "mess_chars_error";
    else chars.className = "mess_chars";
}

// area to check
var areaToCheck = new Array();


// "check form on submit" --> registering textareas
function registerUserInputCheck(areaNmb, maxLen) {
	var i = areaToCheck.length;
	areaToCheck[i] = new Array();
	areaToCheck[i][0] = areaNmb;
	areaToCheck[i][1] = maxLen;
}

// checking user input on submit
function checkUserInput() {
	for (var i = 0; i < areaToCheck.length; i++) {
	    var len = textareas[areaToCheck[i][0]].value.length;
		if (len > areaToCheck[i][1]) {
			alert(len_error_mess);
			return false;
		}
	}
	return true;
}

// is there a helpline??
var isHelpline = false;

// there is a helpline!!
function unlockHelpline() {
	isHelpline = true;
}

// Shows the help messages in the helpline window
function helpline(help) {
	if (isHelpline)	{
		formular.helpbox.value = eval(help + "_help");
	}
}

// Replacement for arrayname.length property
function getarraysize(thearray) {
	for (i = 0; i < thearray.length; i++) {
		if ((thearray[i] == "undefined") || (thearray[i] == "") || (thearray[i] == null)) return i;
	}
	return thearray.length;
}

// Replacement for arrayname.push(value) not implemented in IE until version 5.5
// Appends element to the array
function arraypush(thearray,value) { thearray[ getarraysize(thearray) ] = value; }

// Replacement for arrayname.pop() not implemented in IE until version 5.5
// Removes and returns the last element of an array
function arraypop(thearray) {
	thearraysize = getarraysize(thearray);
	retval = thearray[thearraysize - 1];
	delete thearray[thearraysize - 1];
	return retval;
}

function emoticon(text) {
	if (current_nmb == -1) return;
	text = ' ' + text + ' ';
	insertAtCursorPosition(text);
}

function fontstyle(open, close) {
	if (current_nmb == -1) return;

	editSelection(open,close);
}

var simple = true;
function setSimple(bool) { simple = bool; }

function styleButton(forumCodeNumber) {
	if (current_nmb == -1) return;
	
	
	doNotInsert = false;
	theSelection[current_nmb] = false;
	last = 0;
	textarea.focus();

	if (forumCodeNumber == -2) {
		// Close all open tags & default button names in all textareas
		var i = 0;
		for (i = 0; i < textareas.length; i++) {
			while (forumCode[i][0]) {
				buttonNumber = arraypop(forumCode[i]) - 1;
				textareas[i].value += forumTags[buttonNumber + 1];
				buttext = eval(formular_name + '.addForumCode' + buttonNumber + '.value');
				eval(formular_name + '.addForumCode' + buttonNumber + '.value ="' + buttext.substr(0,(buttext.length - 1)) + '"');
			}
			imageTag[current_nmb] = false; // All tags are closed including image tags :D
		}
		textarea.focus();
		return;
	}
	
	if (forumCodeNumber == -1) {
		// Close all open tags & default button names in the current textarea
		while (forumCode[current_nmb][0]) {
			buttonNumber = arraypop(forumCode[current_nmb]) - 1;
			textarea.value += forumTags[buttonNumber + 1];
			buttext = eval(formular_name + '.addForumCode' + buttonNumber + '.value');
			eval(formular_name + '.addForumCode' + buttonNumber + '.value ="' + buttext.substr(0,(buttext.length - 1)) + '"');
		}
		imageTag[current_nmb] = false; // All tags are closed including image tags :D
		textarea.focus();
		return;
	}
	
	theSelection[current_nmb] = getSelectedText();
	if (theSelection[current_nmb]) {
		// Add tags around selection
		editSelection(forumTags[forumCodeNumber],forumTags[forumCodeNumber+1]);
		textarea.focus();
		theSelection[current_nmb] = '';
		return;
	}
	
	// Find last occurance of an open tag the same as the one just clicked
	for (i = 0; i < forumCode[current_nmb].length; i++) {
		if (forumCode[current_nmb][i] == forumCodeNumber+1) {
			last = i;
			doNotInsert = true;
		}
	}
	
	if (doNotInsert) {
		// Close all open tags up to the one just clicked & default button names
		while (forumCode[current_nmb][last]) {
			buttonNumber = arraypop(forumCode[current_nmb]) - 1;
			if (simple) {
				textarea.value += forumTags[buttonNumber + 1];
			} else {
				insertAtCursorPosition(forumTags[buttonNumber + 1]);
			}
			buttext = eval(formular_name + '.addForumCode' + buttonNumber + '.value');
			eval(formular_name + '.addForumCode' + buttonNumber + '.value ="' + buttext.substr(0,(buttext.length - 1)) + '"');
			imageTag[current_nmb] = false;
		}
		textarea.focus();
		return;
	} else {
		// Open tags
		if (imageTag[current_nmb] && (forumCodeNumber != 14)) {
			// Close image tag before adding another
			if (simple) {
				textarea.value += forumTags[15];
			} else {
				insertAtCursorPosition(forumTags[15]);
			}

			lastValue = arraypop(forumCode[current_nmb]) - 1;	// Remove the close image tag from the list
			formular.addForumCode14.value = "Img";	// Return button back to normal state
			imageTag[current_nmb] = false;
		}
	
		// Open tag
		
		if (simple) {
			textarea.value += forumTags[forumCodeNumber];
		} else {
			insertAtCursorPosition(forumTags[forumCodeNumber]);
		}
		
		if ((forumCodeNumber == 14) && (imageTag[current_nmb] == false)) imageTag[current_nmb] = 1; // Check to stop additional tags after an unclosed image tag
		arraypush(forumCode[current_nmb],forumCodeNumber+1);
		eval(formular_name + '.addForumCode'+forumCodeNumber+'.value += "*"');
		textarea.focus();
		return;
	}
	storeCaret(textarea);
}

function storeCaret(textElement) { if (textElement.createTextRange) textElement.caretPos = document.selection.createRange().duplicate(); }

function colorPalette(text,mt) {
	var r = 0, g = 0, b = 0;
	var numberList = new Array(6);
	numberList[0] = "00"; numberList[1] = "40"; numberList[2] = "80"; numberList[3] = "BF"; numberList[4] = "FF";
	document.writeln('<TABLE CELLSPACING="1" CELLPADDING="0" BORDER="0">');
	document.writeln('<TR><TD COLSPAN="5" CLASS="small">'+text+'</TD></TR>');
	for(r = 0; r < 5; r++) {
		for(g = 0; g < 5; g++) {
			document.writeln('<TR>');
			for(b = 0; b < 5; b++) {
				color = String(numberList[r]) + String(numberList[g]) + String(numberList[b]);
				document.write('<TD BGCOLOR="#' + color + '">');
				document.write('<A HREF="javascript:fontstyle(\'[color=#' + color + ']\', \'[/color]\');" OnMouseOver="helpline(\'s\');"><img src="'+mt+'" width="10" height="6" border="0" alt="#' + color + '" title="#' + color + '" /></A>');
				document.writeln('</TD>');
			}
			document.writeln('</TR>');
		}
	}
	document.writeln('</TABLE>');
}