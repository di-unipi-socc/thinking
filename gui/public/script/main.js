var toggle = true;
	
// This function generates the remote api URL
function getAPI() {
	return "http://" + apiUrl + ":" + apiPort + "/" + apiResource;
}

// This function append a thought-quote at the end of the page
// -- toggle=true --> the quote is placed on the left
// -- toggle=false --> the quote is placed on the right
function appendThought(thought, author, location, toggle) {
	var thoughts = $("#thoughts-div")[0];

	var thoughtBlockQuote = document.createElement("blockquote");
	if(toggle) thoughtBlockQuote.setAttribute("class","blockquote");
	else thoughtBlockQuote.setAttribute("class","blockquote-reverse");
	thoughts.appendChild(thoughtBlockQuote);
	
	var thoughtP = document.createElement("p");
	thoughtP.innerHTML = thought;
	thoughtBlockQuote.appendChild(thoughtP);
	
	var thoughtSmall = document.createElement("small");
	thoughtSmall.innerHTML = author + ", <em>" + location + "</em>";
	thoughtBlockQuote.appendChild(thoughtSmall);	
}

// This function loads all available thoughts on the page
function loadThoughts() {
	var api = getAPI();
	$("#thoughts-div")[0].innerHTML = "";
	var json = $.getJSON(api, printThoughts);
}

// This (callback) function prints all existing thoughts
// (retrieved from the JSON)
function printThoughts(thoughts) {
	for (i=0; i<thoughts.length; i++) {
		var thought = thoughts[i];
		appendThought(thought.thought, thought.author, thought.location,toggle);
		toggle = !toggle;
	}
}

// This function permits adding a new thought
function addThought(thought, author, location) {
	// Clean modal
	$("#new-thought")[0].value = "";
	$("#new-author")[0].value = "";
	$("#new-location")[0].value = "";
	
	// add thought
	if(thought == "") return;
	var postUri = getAPI() + "?thought=" + thought;
	if(author != "" && author != null) postUri += "&author=" + author;
	if(location != "" && location != null)  postUri += "&location=" + location;
	
	$.post(postUri);
	appendThought(thought,author,location);
}

// This function permits saving the thought added through the modal
function saveNewThought() {
	var thought = $("#new-thought")[0].value;
	if (thought == "") {
		alert("Thought cannot be empty");
		return;
	}
	var author = $("#new-author")[0].value;
	var location = $("#new-location")[0].value;
	addThought(thought, author, location);
}
