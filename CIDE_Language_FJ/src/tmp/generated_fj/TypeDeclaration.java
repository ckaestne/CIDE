package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeDeclaration extends GenASTNode {
  public TypeDeclaration(ASTStringNode identifier, ExtendedType extendedType, ArrayList<VarDeclaration> varDeclaration, ClassConstructor classConstructor, ArrayList<MethodDeclaration> methodDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyOne<ExtendedType>("extendedType", extendedType),
      new PropertyZeroOrMore<VarDeclaration>("varDeclaration", varDeclaration),
      new PropertyOne<ClassConstructor>("classConstructor", classConstructor),
      new PropertyZeroOrMore<MethodDeclaration>("methodDeclaration", methodDeclaration)
    }, firstToken, lastToken);
  }
  public TypeDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public ExtendedType getExtendedType() {
    return ((PropertyOne<ExtendedType>)getProperty("extendedType")).getValue();
  }
  public ArrayList<VarDeclaration> getVarDeclaration() {
    return ((PropertyZeroOrMore<VarDeclaration>)getProperty("varDeclaration")).getValue();
  }
  public ClassConstructor getClassConstructor() {
    return ((PropertyOne<ClassConstructor>)getProperty("classConstructor")).getValue();
  }
  public ArrayList<MethodDeclaration> getMethodDeclaration() {
    return ((PropertyZeroOrMore<MethodDeclaration>)getProperty("methodDeclaration")).getValue();
  }
}
