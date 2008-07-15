package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Elision extends GenASTNode {
  public Elision(ArrayList<ASTTextNode> text283, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<ASTTextNode>("text283", text283)
    }, firstToken, lastToken);
  }
  public Elision(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Elision(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<ASTTextNode> getText283() {
    return ((PropertyOneOrMore<ASTTextNode>)getProperty("text283")).getValue();
  }
}
