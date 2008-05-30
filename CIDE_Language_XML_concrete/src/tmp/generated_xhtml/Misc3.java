package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Misc3 extends Misc {
  public Misc3(PI pI, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PI>("pI", pI)
    }, firstToken, lastToken);
  }
  public Misc3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Misc3(cloneProperties(),firstToken,lastToken);
  }
  public PI getPI() {
    return ((PropertyOne<PI>)getProperty("pI")).getValue();
  }
}
