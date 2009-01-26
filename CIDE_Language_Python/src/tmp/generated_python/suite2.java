package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class suite2 extends suite {
  public suite2(ASTStringNode newline, ASTStringNode indent, ArrayList<stmt> stmt, ASTStringNode dedent, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("newline", newline),
      new PropertyOne<ASTStringNode>("indent", indent),
      new PropertyOneOrMore<stmt>("stmt", stmt),
      new PropertyOne<ASTStringNode>("dedent", dedent)
    }, firstToken, lastToken);
  }
  public suite2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new suite2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getNewline() {
    return ((PropertyOne<ASTStringNode>)getProperty("newline")).getValue();
  }
  public ASTStringNode getIndent() {
    return ((PropertyOne<ASTStringNode>)getProperty("indent")).getValue();
  }
  public ArrayList<stmt> getStmt() {
    return ((PropertyOneOrMore<stmt>)getProperty("stmt")).getValue();
  }
  public ASTStringNode getDedent() {
    return ((PropertyOne<ASTStringNode>)getProperty("dedent")).getValue();
  }
}
