package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class klasse extends GenASTNode {
  public klasse(naam naam, klasseTypeVar klasseTypeVar, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<naam>("naam", naam),
      new PropertyOne<klasseTypeVar>("klasseTypeVar", klasseTypeVar)
    }, firstToken, lastToken);
  }
  public klasse(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new klasse(cloneProperties(),firstToken,lastToken);
  }
  public naam getNaam() {
    return ((PropertyOne<naam>)getProperty("naam")).getValue();
  }
  public klasseTypeVar getKlasseTypeVar() {
    return ((PropertyOne<klasseTypeVar>)getProperty("klasseTypeVar")).getValue();
  }
}
