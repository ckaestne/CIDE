package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VariableDeclaration extends GenASTNode {
  public VariableDeclaration(Identifier identifier, Initialiser initialiser, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Identifier>("identifier", identifier),
      new PropertyZeroOrOne<Initialiser>("initialiser", initialiser)
    }, firstToken, lastToken);
  }
  public VariableDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VariableDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public Identifier getIdentifier() {
    return ((PropertyOne<Identifier>)getProperty("identifier")).getValue();
  }
  public Initialiser getInitialiser() {
    return ((PropertyZeroOrOne<Initialiser>)getProperty("initialiser")).getValue();
  }
}
