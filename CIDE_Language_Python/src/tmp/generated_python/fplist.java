package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class fplist extends GenASTNode {
  public fplist(fpdef fpdef, ArrayList<fpdef> fpdef1, ASTTextNode text2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<fpdef>("fpdef", fpdef),
      new PropertyZeroOrMore<fpdef>("fpdef1", fpdef1),
      new PropertyZeroOrOne<ASTTextNode>("text2", text2)
    }, firstToken, lastToken);
  }
  public fplist(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new fplist(cloneProperties(),firstToken,lastToken);
  }
  public fpdef getFpdef() {
    return ((PropertyOne<fpdef>)getProperty("fpdef")).getValue();
  }
  public ArrayList<fpdef> getFpdef1() {
    return ((PropertyZeroOrMore<fpdef>)getProperty("fpdef1")).getValue();
  }
  public ASTTextNode getText2() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text2")).getValue();
  }
}
