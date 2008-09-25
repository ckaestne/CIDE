package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyList;
import cide.gparser.Token;

public class context3 extends context {
  public context3(ArrayList<klasse> klasse1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<klasse>("klasse1", klasse1)
    }, firstToken, lastToken);
  }
  public context3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new context3(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<klasse> getKlasse1() {
    return ((PropertyList<klasse>)getProperty("klasse1")).getValue();
  }
}
