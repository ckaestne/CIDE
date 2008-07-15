package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class JScriptVarDeclaration extends GenASTNode {
  public JScriptVarDeclaration(Identifier identifier, ASTStringNode identifier_name, Initialiser initialiser, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Identifier>("identifier", identifier),
      new PropertyOne<ASTStringNode>("identifier_name", identifier_name),
      new PropertyZeroOrOne<Initialiser>("initialiser", initialiser)
    }, firstToken, lastToken);
  }
  public JScriptVarDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new JScriptVarDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public Identifier getIdentifier() {
    return ((PropertyOne<Identifier>)getProperty("identifier")).getValue();
  }
  public ASTStringNode getIdentifier_name() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier_name")).getValue();
  }
  public Initialiser getInitialiser() {
    return ((PropertyZeroOrOne<Initialiser>)getProperty("initialiser")).getValue();
  }
}
