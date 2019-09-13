// client-side js
// run by the browser each time your view template referencing it is loaded

console.log('hello world :o');

let comments = [];
let cols = [];

// define variables that reference elements on our page
const commentsList = document.getElementById('commenti');
const commentsForm = document.forms[0];
const commentoInput = commentsForm.elements['commento'];
const colonninaInput = commentsForm.elements['colonnina'];

const colsList = document.getElementById('colonnine');
const colsForm = document.forms[1];
const latitudineInput = colsForm.elements['latitudine'];
const longitudineInput = colsForm.elements['longitudine'];
const minpowerkwInput = colsForm.elements['minpowerkw'];

// a helper function to call when our request for dreams is done
const getCommentsListener = function() {
  // parse our response to convert to JSON
  comments = JSON.parse(this.responseText);

  // iterate through every dream and add it to our page
  comments.forEach( function(row) {
    appendNewComment(row.commento + ", colonnina: " + row.colonnina);
  });
}

const getColsListener = function() {
  
  colsList.innerHTML = "";
  // parse our response to convert to JSON
  cols = JSON.parse(this.responseText);

  // iterate through every dream and add it to our page
  cols.forEach( function(row) {
    appendNewCols("Latitudine: " + row.Latitude + ", Longitudine: " + row.Longitude + ", Distanza: " + row.distance);
  });
}

// request the dreams from our app's sqlite database
const commentsRequest = new XMLHttpRequest();
commentsRequest.onload = getCommentsListener;
commentsRequest.open('get', '/getCommenti');
commentsRequest.send();

// a helper function that creates a list item for a given dream
const appendNewComment = function(comment) {
  const newListItem = document.createElement('li');
  newListItem.innerHTML = comment;
  commentsList.appendChild(newListItem);
}

const appendNewCols = function(col) {
  const newListItem = document.createElement('li');
  newListItem.innerHTML = col;
  colsList.appendChild(newListItem);
}

colsForm.onsubmit = function(event) {
  // stop our form submission from refreshing the page
  event.preventDefault();

  const colRequest = new XMLHttpRequest();
colRequest.onload = getColsListener;
colRequest.open('get', '/trova-colonnine/?latitude=' + latitudineInput.value + "&longitude=" + longitudineInput.value + "&minpowerkw=" + minpowerkwInput.value);
colRequest.send();
  
  // reset form 
  latitudineInput.value = '';
  longitudineInput.value = '';
  minpowerkwInput.value = '';
};