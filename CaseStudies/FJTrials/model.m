Project_ : Project+ :: _Project ;

Project : Feature1 Feature2 :: Feature1ANDFeature2
	| Feature3
	| Feature4
	| Feature5
	| Feature6
	| Feature7
	| Feature8XORFeature9 ;

Feature8XORFeature9 : Feature8
	| Feature9 ;

