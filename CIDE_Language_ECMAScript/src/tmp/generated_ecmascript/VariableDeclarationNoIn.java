package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VariableDeclarationNoIn extends GenASTNode {
  public VariableDeclarationNoIn(Identifier identifier, InitialiserNoIn initialiserNoIn, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Identifier>("identifier", identifier),
      new PropertyZeroOrOne<InitialiserNoIn>("initialiserNoIn", initialiserNoIn)
    }, firstToken, lastToken);
  }
  public VariableDeclarationNoIn(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VariableDeclarationNoIn(cloneProperties(),firstToken,lastToken);
  }
  public Identifier getIdentifier() {
    return ((PropertyOne<Identifier>)getProperty("identifier")).getValue();
  }
  public InitialiserNoIn getInitialiserNoIn() {
    return ((PropertyZeroOrOne<InitialiserNoIn>)getProperty("initialiserNoIn")).getValue();
  }
}
