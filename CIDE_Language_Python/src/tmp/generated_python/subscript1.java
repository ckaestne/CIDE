package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class subscript1 extends subscript {
  public subscript1(ASTStringNode dot, ASTStringNode dot1, ASTStringNode dot2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("dot", dot),
      new PropertyOne<ASTStringNode>("dot1", dot1),
      new PropertyOne<ASTStringNode>("dot2", dot2)
    }, firstToken, lastToken);
  }
  public subscript1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new subscript1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getDot() {
    return ((PropertyOne<ASTStringNode>)getProperty("dot")).getValue();
  }
  public ASTStringNode getDot1() {
    return ((PropertyOne<ASTStringNode>)getProperty("dot1")).getValue();
  }
  public ASTStringNode getDot2() {
    return ((PropertyOne<ASTStringNode>)getProperty("dot2")).getValue();
  }
}
