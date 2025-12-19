AlertDialog dialog;

View view = getLayoutInflater()
        .inflate(R.layout.custom_alert_dialog, null);

Button btnYes = view.findViewById(R.id.btnYes);
Button btnNo = view.findViewById(R.id.btnNo);

AlertDialog.Builder builder = new AlertDialog.Builder(this);
builder.setView(view);

dialog = builder.create();
dialog.setCancelable(false);
dialog.show();

btnYes.setOnClickListener(v -> {
    finish(); // Exit app
});

btnNo.setOnClickListener(v -> {
    dialog.dismiss();
});
