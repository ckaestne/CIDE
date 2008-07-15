package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MethodDeclarator extends GenASTNode {
  public MethodDeclarator(JavaIdentifier javaIdentifier, FormalParameters formalParameters, ArrayList<ASTTextNode> text500, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<JavaIdentifier>("javaIdentifier", javaIdentifier),
      new PropertyOne<FormalParameters>("formalParameters", formalParameters),
      new PropertyZeroOrMore<ASTTextNode>("text500", text500)
    }, firstToken, lastToken);
  }
  public MethodDeclarator(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MethodDeclarator(cloneProperties(),firstToken,lastToken);
  }
  public JavaIdentifier getJavaIdentifier() {
    return ((PropertyOne<JavaIdentifier>)getProperty("javaIdentifier")).getValue();
  }
  public FormalParameters getFormalParameters() {
    return ((PropertyOne<FormalParameters>)getProperty("formalParameters")).getValue();
  }
  public ArrayList<ASTTextNode> getText500() {
    return ((PropertyZeroOrMore<ASTTextNode>)getProperty("text500")).getValue();
  }
}
