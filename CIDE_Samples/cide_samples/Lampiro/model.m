Project : [Timing] [Motorola] [Screensaver] [Send_debug1] [UI] derivatives [TLS] [Compression] [BXMPP] [Glider] [not_plain_socket] [Bludeno] :: _Project ;

derivatives : [x_notTiming] [x_notUI] [x_notBXMPP] [x_notCompression] [x_notTLS] [x_notGlider] [x_not_Bludeno] :: _derivatives ;

%%

x_notTiming iff not Timing ;
x_notUI iff not UI ;
x_notBXMPP iff not BXMPP ;
x_notCompression iff not Compression ;
x_notTLS iff not TLS ;
x_notGlider iff not Glider ;
x_not_Bludeno iff not Bludeno ;

