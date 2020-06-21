<?php
 
class DbOperation
{
    //Database connection link
    private $con;
 
    //Class constructor
    function __construct()
    {
        //Getting the DbConnect.php file
        require_once dirname(__FILE__) . '/DbConnect.php';
 
        //Creating a DbConnect object to connect to the database
        $db = new DbConnect();
 
        //Initializing our connection link of this class
        //by calling the method connect of DbConnect class
        $this->con = $db->connect();
    }

	/*
	* The create operation
	* When this method is called a new record is created in the database
	*/
	function createPatient($patient_json){
		$obj = json_decode($patient_json);
		// Attributes
		$hasSymptom = $obj->hasSymptom;
		$hasFever = $obj->hasFever;
		$hasSmellTasteLoss = $obj->hasSmellTasteLoss;
		$hasRunningNose = $obj->hasRunningNose;
		$hasTiredness = $obj->hasTiredness;
		$hasCough = $obj->hasCough;
		$hasBreathProblem = $obj->hasBreathProblem;
		$hasPurpleMouth = $obj->hasPurpleMouth;
		$hasSoreThroat = $obj->hasSoreThroat;
		$hasChestPressure = $obj->hasChestPressure;
		$hasDiarrhea = $obj->hasDiarrhea;
		$hasNOASymptom = $obj->hasNOASymptom;
		$duration1to3Days = $obj->duration1to3Days;
		$duration4to7Days = $obj->duration4to7Days;
		$duration8to10Days = $obj->duration8to10Days;
		$duration11to14Days = $obj->duration11to14Days;
		$duration14PlusDays = $obj->duration14PlusDays;
		$wentOutOfCity = $obj->wentOutOfCity;
		$hadContactWithOutsider = $obj->hadContactWithOutsider;
		$hadContactWithInfected = $obj->hadContactWithInfected;
		$hadLast14DaysNOA = $obj->hadLast14DaysNOA;
		$age1to15Years = $obj->age1to15Years;
		$age16to30Years = $obj->age16to30Years;
		$age31to45Years = $obj->age31to45Years;
		$age46to60Years = $obj->age46to60Years;
		$age60PlusYears = $obj->age60PlusYears;
		$hasDiabetes = $obj->hasDiabetes;
		$hasHeartProblem = $obj->hasHeartProblem;
		$hasChronicKidney = $obj->hasChronicKidney;
		$hasChronicRespiratory = $obj->hasChronicRespiratory;
		$hasHighPressure = $obj->hasHighPressure;
		$hasCancer = $obj->hasCancer;
		$dontHavePriorDisease = $obj->dontHavePriorDisease;
		$priorDiseasesDWA = $obj->priorDiseasesDWA;
		$male = $obj->male;
		$female = $obj->female;
		$otherGender = $obj->otherGender;
		$citySalinas = $obj->citySalinas;
		$cityAracuai = $obj->cityAracuai;
		$cityTaiobeiras = $obj->cityTaiobeiras;
		$cityCoronelMurta = $obj->cityCoronelMurta;
		$citySaoJoaoDoParaiso = $obj->citySaoJoaoDoParaiso;
		$cityJanauba = $obj->cityJanauba;
		$cityPorteirinha = $obj->cityPorteirinha;
		$cityMontesClaros = $obj->cityMontesClaros;
		$otherCity = $obj->otherCity;
		$neighborhoodName = $obj->neighborhoodName;
		$zipCode = $obj->zipCode;
		$fullNameDWA = $obj->fullNameDWA;
		$fullName = $obj->fullName;
		$phone = $obj->phone;
		$email = $obj->email;
		$android_id = $obj->android_id;
		$visitedPoints = $obj->visitedPoints;
				
		$stmt = $this->con->prepare("INSERT INTO patient (hasSymptom, hasFever, hasSmellTasteLoss, hasRunningNose, hasTiredness, hasCough,
				hasBreathProblem, hasPurpleMouth, hasSoreThroat, hasChestPressure, hasDiarrhea, hasNOASymptom, 
				duration1to3Days, duration4to7Days, duration8to10Days, duration11to14Days, duration14PlusDays, wentOutOfCity, 
				hadContactWithOutsider, hadContactWithInfected, hadLast14DaysNOA, age1to15Years, age16to30Years, age31to45Years, 
				age46to60Years, age60PlusYears, hasDiabetes, hasHeartProblem, hasChronicKidney,	hasChronicRespiratory, 
				hasHighPressure, hasCancer, dontHavePriorDisease, priorDiseasesDWA, male, female, 
				otherGender, citySalinas, cityAracuai, cityTaiobeiras, cityCoronelMurta, citySaoJoaoDoParaiso, 
				cityJanauba, cityPorteirinha, cityMontesClaros, otherCity, neighborhoodName, zipCode, 
				fullNameDWA, fullName, phone, email, android_id, visitedPoints) 
			VALUES(?, ?, ?, ?, ?, ?,
				   ?, ?, ?, ?, ?, ?,   
				   ?, ?, ?, ?, ?, ?,   
				   ?, ?, ?, ?, ?, ?,   
				   ?, ?, ?, ?, ?, ?,   
				   ?, ?, ?, ?, ?, ?,   
				   ?, ?, ?, ?, ?, ?,   
				   ?, ?, ?, ?, ?, ?,   
			       ?, ?, ?, ?, ?, ST_GeomFromText(?))");
				   
		$field_types = "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiississsss";		 
		
		if (empty($visitedPoints)) {
			$multipoint_field = null;
		} else {
			$multipoint_field = "MULTIPOINT(" . $visitedPoints . ")"; 
		}	
		
		$stmt->bind_param($field_types, $hasSymptom, $hasFever, $hasSmellTasteLoss, $hasRunningNose, $hasTiredness, $hasCough,
				$hasBreathProblem, $hasPurpleMouth, $hasSoreThroat, $hasChestPressure, $hasDiarrhea, $hasNOASymptom, 
				$duration1to3Days, $duration4to7Days, $duration8to10Days, $duration11to14Days, $duration14PlusDays, $wentOutOfCity, 
				$hadContactWithOutsider, $hadContactWithInfected, $hadLast14DaysNOA, $age1to15Years, $age16to30Years, $age31to45Years, 
				$age46to60Years, $age60PlusYears, $hasDiabetes, $hasHeartProblem, $hasChronicKidney, $hasChronicRespiratory, 
				$hasHighPressure, $hasCancer, $dontHavePriorDisease, $priorDiseasesDWA, $male, $female, 
				$otherGender, $citySalinas, $cityAracuai, $cityTaiobeiras, $cityCoronelMurta, $citySaoJoaoDoParaiso, 
				$cityJanauba, $cityPorteirinha, $cityMontesClaros, $otherCity, $neighborhoodName, $zipCode, 
				$fullNameDWA, $fullName, $phone, $email, $android_id, $multipoint_field
			);
							
		if($stmt->execute())
			return true; 
		return false; 
	}

	/*
	* The read operation
	* When this method is called it is returning all the existing record of the database
	* PARAM $interval_in_days = interval (in days) considered since the creation of the record, according to current_date
	*/
	function getPatients($email, $phone, $interval_in_days){
		$query = "SELECT id, android_id, created_at FROM patient 
				  WHERE ((email <> '' AND email = ? ) OR (phone <> '' AND phone = ? )) 
			  	    AND (DATE(DATE_ADD(created_at, INTERVAL ? DAY))) >= CURRENT_DATE()";
			  
		$patients = array(); 
		
		$stmt = $this->con->prepare($query);
		$stmt->bind_param("ssi", $email, $phone, $interval_in_days);
		if ($stmt->execute()) {
			$stmt->bind_result($id, $android_id, $created_at);
		
			while($stmt->fetch()){
				$patient  = array();
				$patient['id'] = $id; 
				$patient['android_id'] = $android_id; 
				$patient['created_at'] = $created_at; 
			
				array_push($patients, $patient); 
			}
		}	
		
		return $patients; 
	}
	
	/*
	* The update operation
	* When this method is called the record with the given id is updated with the new given values
	*/
/*	function updateHero($id, $name, $realname, $rating, $teamaffiliation){
		$stmt = $this->con->prepare("UPDATE heroes SET name = ?, realname = ?, rating = ?, teamaffiliation = ? WHERE id = ?");
		$stmt->bind_param("ssisi", $name, $realname, $rating, $teamaffiliation, $id);
		if($stmt->execute())
			return true; 
		return false; 
	}
*/	
	
	/*
	* The delete operation
	* When this method is called record is deleted for the given id 
	*/
/*	function deleteHero($id){
		$stmt = $this->con->prepare("DELETE FROM heroes WHERE id = ? ");
		$stmt->bind_param("i", $id);
		if($stmt->execute())
			return true; 
		
		return false; 
	}
	*/
}