package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ReferenceTypeP2 extends ReferenceTypeP {
  public ReferenceTypeP2(ClassOrInterfaceType classOrInterfaceType, ArrayList<ASTTextNode> text506, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ClassOrInterfaceType>("classOrInterfaceType", classOrInterfaceType),
      new PropertyZeroOrMore<ASTTextNode>("text506", text506)
    }, firstToken, lastToken);
  }
  public ReferenceTypeP2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ReferenceTypeP2(cloneProperties(),firstToken,lastToken);
  }
  public ClassOrInterfaceType getClassOrInterfaceType() {
    return ((PropertyOne<ClassOrInterfaceType>)getProperty("classOrInterfaceType")).getValue();
  }
  public ArrayList<ASTTextNode> getText506() {
    return ((PropertyZeroOrMore<ASTTextNode>)getProperty("text506")).getValue();
  }
}
