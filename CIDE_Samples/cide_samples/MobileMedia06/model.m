NewCompound1 : Media+ [Sorting] [SMS] [Favorite] [CopyPhoto] Derivatives :: MobilMedia ;

Media : PhotoAlbum
	| Music ;

Derivatives : [x_CopyPhotoOrSMS] [x_PhotoAlbumOrMusic] [x_NotPhotoAlbum] [x_NotMusic] :: _Derivatives ;

%%

x_CopyPhotoOrSMS iff CopyPhoto or SMS ;
x_PhotoAlbumOrMusic iff PhotoAlbum or Music ;
x_NotPhotoAlbum iff not PhotoAlbum ;
x_NotMusic iff not Music ;
SMS implies PhotoAlbum;
