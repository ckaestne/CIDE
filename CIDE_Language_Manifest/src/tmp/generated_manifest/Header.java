package tmp.generated_manifest;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Header extends GenASTNode {
  public Header(ASTStringNode name, ASTStringNode value, ArrayList<ASTStringNode> value1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("name", name),
      new PropertyOne<ASTStringNode>("value", value),
      new PropertyZeroOrMore<ASTStringNode>("value1", value1)
    }, firstToken, lastToken);
  }
  public Header(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Header(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getName() {
    return ((PropertyOne<ASTStringNode>)getProperty("name")).getValue();
  }
  public ASTStringNode getValue() {
    return ((PropertyOne<ASTStringNode>)getProperty("value")).getValue();
  }
  public ArrayList<ASTStringNode> getValue1() {
    return ((PropertyZeroOrMore<ASTStringNode>)getProperty("value1")).getValue();
  }
}
