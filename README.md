Simple Style Dialog
===================

Simple and stylish dialog for Android

##Setup
Import this library into your project

##Usage

A basic message

	new SimpleStyleDialog(this)
		.setMessage("A basic message")
		.setRightButton("Okay", null)
		.show();

A basic bold message

	new SimpleStyleDialog(this)
		.setTitle("A bold message")
		.setRightButton("Okay", null)
		.show();

A basic message with title and message

	new SimpleStyleDialog(this)
		.setTitle("A Title")
		.setMessage("A Message")
		.setRightButton("Ok", null)
		.show();

Show double buttons and bind listener to it

	new SimpleStyleDialog(this)
		.setTitle("Hello!")
		.setMessage("Do you want to see another dialog?")
		.setRightButton("Sure!", new OnSimpleClickListener() {
			@Override
			public void onClick(SimpleStyleDialog simpleStyleDialog) {
				simpleStyleDialog.dismiss();
				new SimpleStyleDialog(MainActivity.this)
				.setTitle("Another Title")
				.setMessage("Another Message")
				.setRightButton("Cool!", null)
				.show();
			}
		})
		.setLeftButton("No!", null)
		.show();

Show triple buttons

	new SimpleStyleDialog(this)
		.setTitle("Warning")
		.setMessage("Do you want to save your imaginary changes?")
		.setRightButton("Yes", null)
		.setLeftButton("No", null)
		.setMiddleButton("Cancel", null)
		.show();

##TODO
- Text styling
- More animations