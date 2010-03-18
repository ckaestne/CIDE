package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ClassOrInterfaceBody extends GenASTNode {
  public ClassOrInterfaceBody(ArrayList<ClassOrInterfaceBodyDeclaration> classOrInterfaceBodyDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<ClassOrInterfaceBodyDeclaration>("classOrInterfaceBodyDeclaration", classOrInterfaceBodyDeclaration)
    }, firstToken, lastToken);
  }
  public ClassOrInterfaceBody(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ClassOrInterfaceBody(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<ClassOrInterfaceBodyDeclaration> getClassOrInterfaceBodyDeclaration() {
    return ((PropertyZeroOrMore<ClassOrInterfaceBodyDeclaration>)getProperty("classOrInterfaceBodyDeclaration")).getValue();
  }
}
