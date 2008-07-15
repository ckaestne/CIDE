package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expansion_unitMain1 extends expansion_unitMain {
  public expansion_unitMain1(ASTStringNode identifier, Arguments arguments, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyOne<Arguments>("arguments", arguments)
    }, firstToken, lastToken);
  }
  public expansion_unitMain1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expansion_unitMain1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public Arguments getArguments() {
    return ((PropertyOne<Arguments>)getProperty("arguments")).getValue();
  }
}
