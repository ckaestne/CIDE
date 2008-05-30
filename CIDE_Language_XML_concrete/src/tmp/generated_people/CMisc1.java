package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CMisc1 extends CMisc {
  public CMisc1(PI pI, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PI>("pI", pI)
    }, firstToken, lastToken);
  }
  public CMisc1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new CMisc1(cloneProperties(),firstToken,lastToken);
  }
  public PI getPI() {
    return ((PropertyOne<PI>)getProperty("pI")).getValue();
  }
}
