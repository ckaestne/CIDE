package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class javacc_input extends GenASTNode implements ISourceFile {
  public javacc_input(javacc_options javacc_options, ASTStringNode identifier, CompilationUnit compilationUnit, ASTStringNode identifier1, ArrayList<production> production, ASTStringNode eof, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<javacc_options>("javacc_options", javacc_options),
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyOne<CompilationUnit>("compilationUnit", compilationUnit),
      new PropertyOne<ASTStringNode>("identifier1", identifier1),
      new PropertyOneOrMore<production>("production", production),
      new PropertyOne<ASTStringNode>("eof", eof)
    }, firstToken, lastToken);
  }
  public javacc_input(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new javacc_input(cloneProperties(),firstToken,lastToken);
  }
  public javacc_options getJavacc_options() {
    return ((PropertyZeroOrOne<javacc_options>)getProperty("javacc_options")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public CompilationUnit getCompilationUnit() {
    return ((PropertyOne<CompilationUnit>)getProperty("compilationUnit")).getValue();
  }
  public ASTStringNode getIdentifier1() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier1")).getValue();
  }
  public ArrayList<production> getProduction() {
    return ((PropertyOneOrMore<production>)getProperty("production")).getValue();
  }
  public ASTStringNode getEof() {
    return ((PropertyOne<ASTStringNode>)getProperty("eof")).getValue();
  }
}
