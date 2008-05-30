package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class NotationType extends GenASTNode {
  public NotationType(ASTStringNode name, ArrayList<ASTStringNode> name1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("name", name),
      new PropertyZeroOrMore<ASTStringNode>("name1", name1)
    }, firstToken, lastToken);
  }
  public NotationType(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new NotationType(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getName() {
    return ((PropertyOne<ASTStringNode>)getProperty("name")).getValue();
  }
  public ArrayList<ASTStringNode> getName1() {
    return ((PropertyZeroOrMore<ASTStringNode>)getProperty("name1")).getValue();
  }
}
