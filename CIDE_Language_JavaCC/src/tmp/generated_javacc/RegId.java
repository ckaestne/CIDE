package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class RegId extends GenASTNode {
  public RegId(ASTTextNode text475, ASTStringNode identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text475", text475),
      new PropertyOne<ASTStringNode>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public RegId(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new RegId(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText475() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text475")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
}
