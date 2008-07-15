package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EnumBodyInternal extends GenASTNode {
  public EnumBodyInternal(ArrayList<ClassOrInterfaceBodyDeclaration> classOrInterfaceBodyDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<ClassOrInterfaceBodyDeclaration>("classOrInterfaceBodyDeclaration", classOrInterfaceBodyDeclaration)
    }, firstToken, lastToken);
  }
  public EnumBodyInternal(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EnumBodyInternal(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<ClassOrInterfaceBodyDeclaration> getClassOrInterfaceBodyDeclaration() {
    return ((PropertyZeroOrMore<ClassOrInterfaceBodyDeclaration>)getProperty("classOrInterfaceBodyDeclaration")).getValue();
  }
}
