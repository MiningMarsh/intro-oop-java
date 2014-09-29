packageedu.gatech.cs1331.companygui;

importjava.awt.BorderLayout;
importjava.awt.Component;
importjava.awt.Frame;
importjava.awt.GridLayout;
importjava.awt.event.ActionEvent;
importjava.text.DateFormat;
importjava.util.Date;
importjavax.swing.AbstractAction;
importjavax.swing.Action;
importjavax.swing.BorderFactory;
importjavax.swing.Box;
importjavax.swing.BoxLayout;
importjavax.swing.JButton;
importjavax.swing.JDialog;
importjavax.swing.JLabel;
importjavax.swing.JOptionPane;
importjavax.swing.JPanel;
importjavax.swing.JTextField;

publicclassHourlyEmployeeDialogextendsJDialog{

privatebooleandismissedWithOk;
privateHourlyEmployeeemployee;
privateJTextFieldnameTextField;
privateJTextFieldhireDateTextField;
privateJTextFieldhourlyWageTextField;
privateJTextFieldmonthlyHoursTextField;
privateStringvalidationErrors;
/**
*Usethisconstructorifyou'recreatinganewemployee.
*/
publicHourlyEmployeeDialog(Frameparent){
this(parent,"NewEmployee",newHourlyEmployee(""));
}

/**
*Usethisconstructorifyou'reeditinganexistingemployee.
*/
publicHourlyEmployeeDialog(Frameparent,
StringdialogTitle,
HourlyEmployeeemployee){
super(parent,dialogTitle);
super.setModal(true);
super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
this.employee=employee;

super.getContentPane().setLayout(newBorderLayout());
JPanelmainPanel=newJPanel();
//We'reonlyputtingonethinginhere,butweneeditto
//growandshrinkasthedialogisresized.Usinga
//GridLayoutisacheapwaytoaccomplishthis.
mainPanel.setLayout(newGridLayout());
mainPanel.setBorder(BorderFactory.createRaisedBevelBorder());
mainPanel.add(createDataEntryPanel());
super.getContentPane().add(mainPanel,BorderLayout.CENTER);
super.getContentPane().add(createButtonPanel(),BorderLayout.SOUTH);
}

privateJPanelcreateDataEntryPanel(){
JLabelnameLabel=newJLabel("Name:");
nameTextField=newJTextField(employee.getName(),20);
JLabelhireDateLabel=newJLabel("HireDate:");
hireDateTextField=
newJTextField(employee.getHireDate().toString(),20);

JPanelp=newJPanel();
p.setLayout(newBoxLayout(p,BoxLayout.Y_AXIS));
p.add(Box.createHorizontalGlue());
p.add(createDataEntryComponent(nameLabel,nameTextField));
p.add(createDataEntryComponent(hireDateLabel,hireDateTextField));

//Uptothispointeachdifferentemployeedialogwouldlookthesame.
//Howcouldwerefactorthedialogstoeliminatecodeduplication?

JLabelhourlyWageLabel=newJLabel("HourlyWage:");
hourlyWageTextField=
newJTextField(newDouble(employee.getHourlyWage()).toString(),20);
p.add(createDataEntryComponent(hourlyWageLabel,hourlyWageTextField));
JLabelmonthlyHoursLabel=newJLabel("MonthlyHours:");
monthlyHoursTextField=
newJTextField(newDouble(employee.getMonthlyHours()).toString(),20);
p.add(createDataEntryComponent(monthlyHoursLabel,monthlyHoursTextField));

returnp;
}

privateComponentcreateDataEntryComponent(JLabellabel,Componententry){
//BoxisaconvenientwaytouseaBoxLayout
Boxb=newBox(BoxLayout.X_AXIS);
b.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
b.add(label);
b.add(Box.createHorizontalGlue());
b.add(entry);
returnb;
}

privateJPanelcreateButtonPanel(){
ActionokAction=newAbstractAction("OK"){
publicvoidactionPerformed(ActionEventevt){
dismissedWithOk=true;
if(isInputValid()){
setVisible(false);
dispose();
}else{
JOptionPane.showMessageDialog(HourlyEmployeeDialog.this,
validationErrors,
"InvalidInput",
JOptionPane.ERROR_MESSAGE);
}
}
};
JButtonokButton=newJButton(okAction);
ActioncancelAction=newAbstractAction("Cancel"){
publicvoidactionPerformed(ActionEventevt){
dismissedWithOk=false;
setVisible(false);
dispose();
}
};
JButtoncancelButton=newJButton(cancelAction);
JPanelp=newJPanel();
p.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
p.setLayout(newBoxLayout(p,BoxLayout.X_AXIS));
p.add(Box.createHorizontalGlue());
p.add(okButton);
p.add(Box.createHorizontalStrut(5));
p.add(cancelButton);
p.add(Box.createHorizontalGlue());

super.getRootPane().setDefaultButton(okButton);
returnp;
}

publicbooleanshowDialog(){
super.setLocationRelativeTo(super.getParent());
super.pack();
super.setVisible(true);
returndismissedWithOk;
}

privatebooleanisInputValid(){
booleanisValid=false;
try{
DateFormatdf=DateFormat.getDateInstance();
employee=
newHourlyEmployee(nameTextField.getText(),
df.parse(hireDateTextField.getText()),
Double.parseDouble(hourlyWageTextField.getText()),
Double.parseDouble(monthlyHoursTextField.getText()));
isValid=true;
}catch(Exceptione){
validationErrors=e.getMessage();
}
returnisValid;
}

publicHourlyEmployeegetEmployee(){
returnemployee;
}

publicstaticvoidmain(String[]args){
HourlyEmployeeDialogsed=newHourlyEmployeeDialog(null);
booleanret=sed.showDialog();
System.out.print("showDialog()returnedandwasdismissedwith");
if(ret){
System.out.println("OK");
}else{
System.out.println("Cancel");
}
System.out.println(sed.getEmployee());
}
}
