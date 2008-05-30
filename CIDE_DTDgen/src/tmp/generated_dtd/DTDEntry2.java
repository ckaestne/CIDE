package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DTDEntry2 extends DTDEntry {
  public DTDEntry2(AttListDecl attListDecl, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AttListDecl>("attListDecl", attListDecl)
    }, firstToken, lastToken);
  }
  public DTDEntry2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new DTDEntry2(cloneProperties(),firstToken,lastToken);
  }
  public AttListDecl getAttListDecl() {
    return ((PropertyOne<AttListDecl>)getProperty("attListDecl")).getValue();
  }
}
