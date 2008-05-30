package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class context2 extends context {
  public context2(ArrayList<klasse> klasse1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<klasse>("klasse1", klasse1)
    }, firstToken, lastToken);
  }
  public context2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new context2(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<klasse> getKlasse1() {
    return ((PropertyList<klasse>)getProperty("klasse1")).getValue();
  }
}
