package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ExtendsList extends GenASTNode {
  public ExtendsList(ArrayList<ClassOrInterfaceType> classOrInterfaceType, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<ClassOrInterfaceType>("classOrInterfaceType", classOrInterfaceType)
    }, firstToken, lastToken);
  }
  public ExtendsList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ExtendsList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<ClassOrInterfaceType> getClassOrInterfaceType() {
    return ((PropertyList<ClassOrInterfaceType>)getProperty("classOrInterfaceType")).getValue();
  }
}
