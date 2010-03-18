package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MethodDeclarator extends GenASTNode {
  public MethodDeclarator(ASTStringNode identifier, FormalParameters formalParameters, ArrayList<ASTTextNode> text21, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyOne<FormalParameters>("formalParameters", formalParameters),
      new PropertyZeroOrMore<ASTTextNode>("text21", text21)
    }, firstToken, lastToken);
  }
  public MethodDeclarator(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MethodDeclarator(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public FormalParameters getFormalParameters() {
    return ((PropertyOne<FormalParameters>)getProperty("formalParameters")).getValue();
  }
  public ArrayList<ASTTextNode> getText21() {
    return ((PropertyZeroOrMore<ASTTextNode>)getProperty("text21")).getValue();
  }
}
