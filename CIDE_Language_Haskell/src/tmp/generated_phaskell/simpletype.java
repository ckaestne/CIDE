package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class simpletype extends GenASTNode {
  public simpletype(ASTStringNode constructor_id, ASTStringNode finduntilequals, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("constructor_id", constructor_id),
      new PropertyZeroOrOne<ASTStringNode>("finduntilequals", finduntilequals)
    }, firstToken, lastToken);
  }
  public simpletype(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new simpletype(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getConstructor_id() {
    return ((PropertyOne<ASTStringNode>)getProperty("constructor_id")).getValue();
  }
  public ASTStringNode getFinduntilequals() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("finduntilequals")).getValue();
  }
}
