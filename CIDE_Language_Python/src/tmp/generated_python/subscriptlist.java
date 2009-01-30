package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class subscriptlist extends GenASTNode {
  public subscriptlist(subscript subscript, ArrayList<subscript> subscript1, ASTTextNode text586, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<subscript>("subscript", subscript),
      new PropertyZeroOrMore<subscript>("subscript1", subscript1),
      new PropertyZeroOrOne<ASTTextNode>("text586", text586)
    }, firstToken, lastToken);
  }
  public subscriptlist(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new subscriptlist(cloneProperties(),firstToken,lastToken);
  }
  public subscript getSubscript() {
    return ((PropertyOne<subscript>)getProperty("subscript")).getValue();
  }
  public ArrayList<subscript> getSubscript1() {
    return ((PropertyZeroOrMore<subscript>)getProperty("subscript1")).getValue();
  }
  public ASTTextNode getText586() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text586")).getValue();
  }
}
