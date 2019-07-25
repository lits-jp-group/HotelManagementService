# HotelManagementService

To send request for thirdPartyApi:
rest api - localhost:8075/places
post command (json)(for Lviv location):
{
 "provider":"google",
  "type":"bar",
  "radius":500,
  "lat":49.83826,
  "lng":24.02324
}

GET TOKEN
localhost:8075/login
{
	"lastName": "Claus",
	"password":"123"
}
token = eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWRDbGFpbSI6MX0.tVDPZwWDT9obiXM7vwmyMSTosVeupMo59jIInR3VC6c

save new StaffMember
localhost:8075/save
POST 
{
	"firstName":"new",
	"role":"admin",
	"lastName": "admin2",
	"password":"123"
}