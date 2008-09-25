package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyOneOrMore;
import cide.gparser.Token;

public class klasse extends GenASTNode {
  public klasse(naam naam, ArrayList<klasseTypeVar> klasseTypeVar, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<naam>("naam", naam),
      new PropertyOneOrMore<klasseTypeVar>("klasseTypeVar", klasseTypeVar)
    }, firstToken, lastToken);
  }
  public klasse(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new klasse(cloneProperties(),firstToken,lastToken);
  }
  public naam getNaam() {
    return ((PropertyOne<naam>)getProperty("naam")).getValue();
  }
  public ArrayList<klasseTypeVar> getKlasseTypeVar() {
    return ((PropertyOneOrMore<klasseTypeVar>)getProperty("klasseTypeVar")).getValue();
  }
}
