
task :echo do
    puts "javac -cp build/classes -d build/classes src/leetcode/TestPreorderTraversal.java"
    puts "java -cp build/classes:lib/junit.jar:lib/hamcrest-core.jar org.junit.runner.JUnitCore TestPreorderTraversal"
end

src_files = FileList.new("src/leetcode/*.java")
src_files.each do |f|
    file class_file => f do
        sh "javac -cp build/classes -d build/classes #{f}"
    end
end

desc "compile and test"
task :test do
    sh "java -cp build/classes:lib/junit.jar:lib/hamcrest-core.jar org.junit.runner.JUnitCore Test*.class"
end
