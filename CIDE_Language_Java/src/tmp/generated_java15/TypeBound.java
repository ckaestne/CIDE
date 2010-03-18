package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeBound extends GenASTNode {
  public TypeBound(ClassOrInterfaceType classOrInterfaceType, ArrayList<ClassOrInterfaceType> classOrInterfaceType1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ClassOrInterfaceType>("classOrInterfaceType", classOrInterfaceType),
      new PropertyZeroOrMore<ClassOrInterfaceType>("classOrInterfaceType1", classOrInterfaceType1)
    }, firstToken, lastToken);
  }
  public TypeBound(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeBound(cloneProperties(),firstToken,lastToken);
  }
  public ClassOrInterfaceType getClassOrInterfaceType() {
    return ((PropertyOne<ClassOrInterfaceType>)getProperty("classOrInterfaceType")).getValue();
  }
  public ArrayList<ClassOrInterfaceType> getClassOrInterfaceType1() {
    return ((PropertyZeroOrMore<ClassOrInterfaceType>)getProperty("classOrInterfaceType1")).getValue();
  }
}
